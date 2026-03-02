import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    private String loggedInUser; // To store the username

    // Constructor now accepts a String 'username'
    public Dashboard(String username) {
        this.loggedInUser = username;

        setTitle("IronPulse Hub - Welcome " + username);
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1, 10, 10));
        getContentPane().setBackground(new Color(26, 26, 26));

        String[] nav = {"Workout Planner", "Daily Exercise", "Progress Tracker", "Workout History", "Fitness Shop", "Logout"};
        for (String text : nav) {
            JButton btn = new JButton(text);
            btn.setBackground(new Color(0, 255, 136));
            btn.addActionListener(e -> {
                if (text.equals("Logout")) {
                    dispose();
                    new FitnessLoginSwing();
                }
                else if (text.equals("Workout Planner")) new WorkoutPlanner().setVisible(true);
                else if (text.equals("Daily Exercise")) new DailyExercise().setVisible(true);
                else if (text.equals("Progress Tracker")) new ProgressTracker().setVisible(true);
                else if (text.equals("Workout History")) new WorkoutHistory().setVisible(true);
                    // SOLUTION: Pass the loggedInUser string here
                else if (text.equals("Fitness Shop")) new FitnessShop(loggedInUser).setVisible(true);
            });
            add(btn);
        }
        setLocationRelativeTo(null);
        setVisible(true);
    }
}