import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class DailyExercise extends JFrame {

    private DefaultListModel<String> logModel;
    private JList<String> logList;

    private JTextField exerciseField;
    private JTextField setsField;
    private JTextField repsField;
    private JTextField weightField;

    public DailyExercise() {

        setTitle("Daily Exercise Log");
        setSize(550,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));


        JPanel inputPanel = new JPanel(new GridLayout(5,2,10,10));

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

        JButton addBtn = new JButton("Add Daily Log");
        inputPanel.add(addBtn);

        add(inputPanel,BorderLayout.NORTH);


        logModel = new DefaultListModel<>();
        logList = new JList<>(logModel);
        add(new JScrollPane(logList),BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();

        JButton deleteBtn = new JButton("Delete Log");
        JButton clearBtn = new JButton("Clear All");

        bottomPanel.add(deleteBtn);
        bottomPanel.add(clearBtn);

        add(bottomPanel,BorderLayout.SOUTH);



        addBtn.addActionListener(e -> {

            String exercise = exerciseField.getText();
            String sets = setsField.getText();
            String reps = repsField.getText();
            String weight = weightField.getText();

            if(exercise.isEmpty() || sets.isEmpty() || reps.isEmpty() || weight.isEmpty()){
                JOptionPane.showMessageDialog(this,"Please fill all fields!");
                return;
            }

            String today = LocalDate.now().toString();

            String logEntry = today + " | " +
                    exercise + " | Sets:" + sets +
                    " | Reps:" + reps +
                    " | Weight:" + weight + "kg";

            logModel.addElement(logEntry);

            exerciseField.setText("");
            setsField.setText("");
            repsField.setText("");
            weightField.setText("");
        });

        deleteBtn.addActionListener(e -> {
            int index = logList.getSelectedIndex();
            if(index != -1){
                logModel.remove(index);
            }
        });

        clearBtn.addActionListener(e -> {
            logModel.clear();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new DailyExercise();
    }
}