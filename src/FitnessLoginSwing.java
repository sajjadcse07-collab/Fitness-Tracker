import javax.swing.*;
import java.awt.*;

public class FitnessLoginSwing extends JFrame {
    public FitnessLoginSwing() {
        setTitle(" Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(26, 26, 26));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("YOUR JOURNEY STARTS HERE", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22)); // Reduced slightly for fit
        title.setForeground(new Color(0, 255, 136));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE); // Make label visible on dark bg
        panel.add(userLabel, gbc);

        JTextField userField = new JTextField(15);
        gbc.gridx = 1; panel.add(userField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        panel.add(passLabel, gbc);

        JPasswordField passField = new JPasswordField(15);
        gbc.gridx = 1; panel.add(passField, gbc);

        JButton loginBtn = new JButton("LOGIN");
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(loginBtn, gbc);

        JButton regBtn = new JButton("Register New Account");
        gbc.gridy = 4;
        panel.add(regBtn, gbc);

        // --- THE FIX IS HERE ---
        loginBtn.addActionListener(e -> {
            String username = userField.getText(); // 1. Get the username
            if(!username.isEmpty()) {
                this.dispose();
                new Dashboard(username); // 2. Pass it to the Dashboard constructor
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a username!");
            }
        });

        regBtn.addActionListener(e -> {
            this.dispose();
            new RegistrationPage();
        });

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}