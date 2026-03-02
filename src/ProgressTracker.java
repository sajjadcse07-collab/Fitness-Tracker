import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProgressTracker extends JFrame {

    private DefaultListModel<String> progressModel;
    private JList<String> progressList;
    private JTextField bodyWeightField, strengthField;

    public ProgressTracker() {
        setTitle("Fitness Progress Tracker");
        setSize(550, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));


        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.add(new JLabel("Body Weight (kg):"));
        bodyWeightField = new JTextField();
        inputPanel.add(bodyWeightField);

        inputPanel.add(new JLabel("Strength Improvement:"));
        strengthField = new JTextField();
        inputPanel.add(strengthField);

        JButton addBtn = new JButton("Add Progress");
        inputPanel.add(addBtn);
        add(inputPanel, BorderLayout.NORTH);


        progressModel = new DefaultListModel<>();
        loadProgressFromDisk(); // BACKEND: Load on startup

        progressList = new JList<>(progressModel);
        add(new JScrollPane(progressList), BorderLayout.CENTER);


        JPanel bottomPanel = new JPanel();
        JButton editBtn = new JButton("Edit");
        JButton deleteBtn = new JButton("Delete");
        bottomPanel.add(editBtn);
        bottomPanel.add(deleteBtn);
        add(bottomPanel, BorderLayout.SOUTH);


        addBtn.addActionListener(e -> {
            String weight = bodyWeightField.getText();
            String strength = strengthField.getText();

            if (weight.isEmpty() || strength.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }

            String record = LocalDate.now() + " | Weight: " + weight + "kg | Strength: " + strength;
            progressModel.addElement(record);
            saveProgressToDisk(); // BACKEND: Save after adding

            bodyWeightField.setText("");
            strengthField.setText("");
        });

        editBtn.addActionListener(e -> {
            int index = progressList.getSelectedIndex();
            if (index != -1) {
                String newValue = JOptionPane.showInputDialog(this, "Edit Progress:", progressModel.get(index));
                if (newValue != null && !newValue.isEmpty()) {
                    progressModel.set(index, newValue);
                    saveProgressToDisk(); // BACKEND: Save after editing
                }
            }
        });

        deleteBtn.addActionListener(e -> {
            int index = progressList.getSelectedIndex();
            if (index != -1) {
                progressModel.remove(index);
                saveProgressToDisk(); // BACKEND: Save after deleting
            }
        });

        setLocationRelativeTo(null);
    }

    private void saveProgressToDisk() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < progressModel.size(); i++) {
            data.add(progressModel.getElementAt(i));
        }
        DataManager.saveToFile(data, "progress_data.txt");
    }

    private void loadProgressFromDisk() {
        List<String> savedData = DataManager.loadData("progress_data.txt");
        for (String line : savedData) {
            progressModel.addElement(line);
        }
    }
}