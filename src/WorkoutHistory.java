import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WorkoutHistory extends JFrame {

    private DefaultListModel<String> historyModel;
    private JList<String> historyList;
    private JTextField exerciseField, setsField, repsField, weightField;

    public WorkoutHistory() {
        setTitle("Workout History");
        setSize(600, 500);
        // Changed to DISPOSE so the app stays open when this window closes
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ===== Input Panel =====
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.add(new JLabel("Exercise Name:"));
        exerciseField = new JTextField();
        inputPanel.add(exerciseField);

        inputPanel.add(new JLabel("Sets:"));
        setsField = new JTextField();
        inputPanel.add(setsField);

        inputPanel.add(new JLabel("Reps:"));
        repsField = new JTextField();
        inputPanel.add(repsField);

        inputPanel.add(new JLabel("Weight (kg):"));
        weightField = new JTextField();
        inputPanel.add(weightField);

        JButton addBtn = new JButton("Add To History");
        inputPanel.add(addBtn);
        add(inputPanel, BorderLayout.NORTH);

        // ===== History List =====
        historyModel = new DefaultListModel<>();
        // BACKEND INTEGRATION: Load data on startup
        loadHistoryFromDisk();

        historyList = new JList<>(historyModel);
        add(new JScrollPane(historyList), BorderLayout.CENTER);

        // ===== Bottom Buttons =====
        JPanel bottomPanel = new JPanel();
        JButton deleteBtn = new JButton("Delete Selected");
        JButton clearBtn = new JButton("Clear History");
        bottomPanel.add(deleteBtn);
        bottomPanel.add(clearBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        // ===== Button Actions =====
        addBtn.addActionListener(e -> {
            String exercise = exerciseField.getText();
            String sets = setsField.getText();
            String reps = repsField.getText();
            String weight = weightField.getText();

            if (exercise.isEmpty() || sets.isEmpty() || reps.isEmpty() || weight.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }

            String logEntry = LocalDate.now() + " | " + exercise + " | Sets:" + sets + " | Reps:" + reps + " | Weight:" + weight + "kg";
            historyModel.addElement(logEntry);
            saveHistoryToDisk(); // BACKEND: Save after adding

            exerciseField.setText("");
            setsField.setText("");
            repsField.setText("");
            weightField.setText("");
        });

        deleteBtn.addActionListener(e -> {
            int index = historyList.getSelectedIndex();
            if (index != -1) {
                historyModel.remove(index);
                saveHistoryToDisk(); // BACKEND: Save after deleting
            }
        });

        clearBtn.addActionListener(e -> {
            historyModel.clear();
            saveHistoryToDisk(); // BACKEND: Save after clearing
        });

        setLocationRelativeTo(null);
    }

    // Helper methods for Backend Integration
    private void saveHistoryToDisk() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < historyModel.size(); i++) {
            data.add(historyModel.getElementAt(i));
        }
        DataManager.saveData(data, "workout_history.txt");
    }

    private void loadHistoryFromDisk() {
        List<String> savedData = DataManager.loadData("workout_history.txt");
        for (String line : savedData) {
            historyModel.addElement(line);
        }
    }
}