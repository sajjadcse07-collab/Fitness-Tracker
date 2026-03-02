import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WorkoutPlanner extends JFrame {

    private DefaultListModel<String> workoutListModel;
    private JList<String> workoutList;
    private JTextField workoutField;
    private JComboBox<String> categoryBox;

    public WorkoutPlanner() {

        setTitle("Workout Planner");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== Top Panel =====
        JPanel inputPanel = new JPanel(new GridLayout(3,2,10,10));

        inputPanel.add(new JLabel("Workout Name:"));
        workoutField = new JTextField();
        inputPanel.add(workoutField);

        inputPanel.add(new JLabel("Category:"));
        String[] categories = {"Chest","Legs","Back","Cardio","Arms","Shoulders"};
        categoryBox = new JComboBox<>(categories);
        inputPanel.add(categoryBox);

        JButton addBtn = new JButton("Add Workout");
        inputPanel.add(addBtn);

        add(inputPanel, BorderLayout.NORTH);

        // ===== Center List =====
        workoutListModel = new DefaultListModel<>();
        workoutList = new JList<>(workoutListModel);
        add(new JScrollPane(workoutList), BorderLayout.CENTER);

        // ===== Bottom Buttons =====
        JPanel buttonPanel = new JPanel();

        JButton editBtn = new JButton("Edit");
        JButton deleteBtn = new JButton("Delete");

        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        // ===== Button Actions =====

        // Add Workout
        addBtn.addActionListener(e -> {
            String name = workoutField.getText();
            String category = (String) categoryBox.getSelectedItem();

            if(!name.isEmpty()){
                workoutListModel.addElement(category + " - " + name);
                workoutField.setText("");
            } else {
                JOptionPane.showMessageDialog(this,"Enter workout name!");
            }
        });

        // Edit Workout
        editBtn.addActionListener(e -> {
            int index = workoutList.getSelectedIndex();

            if(index != -1){
                String newWorkout = JOptionPane.showInputDialog(
                        this,
                        "Edit Workout:",
                        workoutListModel.get(index)
                );

                if(newWorkout != null && !newWorkout.isEmpty()){
                    workoutListModel.set(index,newWorkout);
                }
            }
        });

        // Delete Workout
        deleteBtn.addActionListener(e -> {
            int index = workoutList.getSelectedIndex();

            if(index != -1){
                workoutListModel.remove(index);
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new WorkoutPlanner();
    }
}