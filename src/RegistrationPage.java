import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegistrationPage extends JFrame {

    private JTextField nameField, emailField, usernameField;
    private JPasswordField passwordField, confirmPasswordField;
    private JRadioButton maleBtn, femaleBtn;

    public RegistrationPage() {

        setTitle("IronPulse Fitness - Registration");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== Main Panel =====
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(26,26,26)); // Dark Theme
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ===== Title =====
        JLabel title = new JLabel("CREATE ACCOUNT", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(new Color(0,255,136));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        gbc.gridwidth = 1;

        // ===== Full Name =====
        gbc.gridy++;
        panel.add(createLabel("Full Name:"), gbc);
        gbc.gridx = 1;
        nameField = createTextField();
        panel.add(nameField, gbc);

        // ===== Email =====
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(createLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = createTextField();
        panel.add(emailField, gbc);

        // ===== Username =====
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(createLabel("Username:"), gbc);
        gbc.gridx = 1;
        usernameField = createTextField();
        panel.add(usernameField, gbc);

        // ===== Password =====
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(createLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = createPasswordField();
        panel.add(passwordField, gbc);

        // ===== Confirm Password =====
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(createLabel("Confirm Password:"), gbc);
        gbc.gridx = 1;
        confirmPasswordField = createPasswordField();
        panel.add(confirmPasswordField, gbc);

        // ===== Gender =====
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(createLabel("Gender:"), gbc);
        gbc.gridx = 1;
        maleBtn = new JRadioButton("Male");
        femaleBtn = new JRadioButton("Female");

        maleBtn.setBackground(new Color(26,26,26));
        femaleBtn.setBackground(new Color(26,26,26));
        maleBtn.setForeground(Color.WHITE);
        femaleBtn.setForeground(Color.WHITE);

        ButtonGroup group = new ButtonGroup();
        group.add(maleBtn);
        group.add(femaleBtn);

        JPanel genderPanel = new JPanel();
        genderPanel.setBackground(new Color(26,26,26));
        genderPanel.add(maleBtn);
        genderPanel.add(femaleBtn);

        panel.add(genderPanel, gbc);

        // ===== Register Button =====
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;

        JButton registerBtn = new JButton("REGISTER");
        registerBtn.setBackground(new Color(0,255,136));
        registerBtn.setForeground(Color.BLACK);
        registerBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        registerBtn.setFocusPainted(false);

        panel.add(registerBtn, gbc);

        // ===== Button Action =====
        registerBtn.addActionListener(e -> registerUser());

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(new Color(187,187,187));
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField(15);
        field.setBackground(new Color(45,45,45));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        return field;
    }

    private JPasswordField createPasswordField() {
        JPasswordField field = new JPasswordField(15);
        field.setBackground(new Color(45,45,45));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        return field;
    }

    private void registerUser() {

        String name = nameField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if(name.isEmpty() || email.isEmpty() || username.isEmpty()
                || password.isEmpty() || confirmPassword.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Please fill all fields!");
            return;
        }

        if(!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Passwords do not match!");
            return;
        }

        JOptionPane.showMessageDialog(this,
                "Registration Successful!\nWelcome to IronPulse 💪");

        // Clear fields after registration
        nameField.setText("");
        emailField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }

    public static void main(String[] args) {
        new RegistrationPage();
    }
}