package ui;

import dao.UserDAO;
import model.User;
import ui.components.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SignupPage {

    private JFrame frame;

    private RoundedTextField usernameField;
    private RoundedTextField emailField;
    private RoundedPasswordField passwordField;
    private RoundedPasswordField confirmPasswordField;

    private RoundedButton signupButton;
    private RoundedButton backButton;

    public SignupPage() {

        AppTheme.apply();

        frame = new JFrame("EventEase - Create Account");
        frame.setSize(950,720);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel root = new JPanel(new GridLayout(1,2));

        //========================
        // LEFT PANEL
        //========================

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(AppColors.PRIMARY);
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));
        leftPanel.setBorder(new EmptyBorder(80,50,80,50));

        JLabel logo = new JLabel("EVENTEASE");
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setFont(new Font("Segoe UI",Font.BOLD,36));
        logo.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Create Your Account");
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setFont(AppFonts.SUBTITLE);
        subtitle.setForeground(Color.WHITE);

        JLabel l1 = new JLabel("✔ Secure Registration");
        JLabel l2 = new JLabel("✔ Fast Signup");
        JLabel l3 = new JLabel("✔ Easy Booking");
                l1.setAlignmentX(Component.CENTER_ALIGNMENT);
        l2.setAlignmentX(Component.CENTER_ALIGNMENT);
        l3.setAlignmentX(Component.CENTER_ALIGNMENT);

        l1.setForeground(Color.WHITE);
        l2.setForeground(Color.WHITE);
        l3.setForeground(Color.WHITE);

        l1.setFont(AppFonts.TEXT);
        l2.setFont(AppFonts.TEXT);
        l3.setFont(AppFonts.TEXT);

        leftPanel.add(Box.createVerticalGlue());

        leftPanel.add(logo);
        leftPanel.add(Box.createRigidArea(new Dimension(0,15)));

        leftPanel.add(subtitle);
        leftPanel.add(Box.createRigidArea(new Dimension(0,50)));

        leftPanel.add(l1);
        leftPanel.add(Box.createRigidArea(new Dimension(0,15)));

        leftPanel.add(l2);
        leftPanel.add(Box.createRigidArea(new Dimension(0,15)));

        leftPanel.add(l3);

        leftPanel.add(Box.createVerticalGlue());

        //========================
        // RIGHT PANEL
        //========================

        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(AppColors.BACKGROUND);

        ShadowPanel card = new ShadowPanel(30);
        card.setBackground(AppColors.CARD);
        card.setPreferredSize(new Dimension(420,560 ));
        card.setBorder(new EmptyBorder(35, 35, 35, 35));

        GridBagLayout layout = new GridBagLayout();
        card.setLayout(layout);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8,25,8,25);
        gbc.weightx = 1;
                JLabel heading = new JLabel("Create Account");
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setFont(AppFonts.HEADING);
        heading.setForeground(Color.WHITE);

        JLabel info = new JLabel("Join EventEase Today");
        info.setHorizontalAlignment(SwingConstants.CENTER);
        info.setFont(AppFonts.SUBTITLE);
        info.setForeground(AppColors.TEXT_SECONDARY);

        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(AppFonts.LABEL);
        userLabel.setForeground(Color.WHITE);

        usernameField = new RoundedTextField(20);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(AppFonts.LABEL);
        emailLabel.setForeground(Color.WHITE);

        emailField = new RoundedTextField(20);

        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(AppFonts.LABEL);
        passLabel.setForeground(Color.WHITE);

        passwordField = new RoundedPasswordField(20);

        JLabel confirmLabel = new JLabel("Confirm Password");
        confirmLabel.setFont(AppFonts.LABEL);
        confirmLabel.setForeground(Color.WHITE);

        confirmPasswordField = new RoundedPasswordField(20);

        signupButton = new RoundedButton("CREATE ACCOUNT");
        signupButton.setButtonColor(AppColors.SUCCESS);
        signupButton.setHoverColor(AppColors.SUCCESS_HOVER);

        backButton = new RoundedButton("BACK TO LOGIN");
        GridBagConstraints titleGbc = new GridBagConstraints();

        titleGbc.gridx = 0;
        titleGbc.gridy = 0;
        titleGbc.insets = new Insets(5, 25, 5, 25);
        titleGbc.anchor = GridBagConstraints.CENTER;

        card.add(heading, titleGbc);

        titleGbc.gridy = 1;
        titleGbc.insets = new Insets(0, 25, 20, 25);

        card.add(info, titleGbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(20,25,5,25);
        card.add(userLabel, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(0,25,10,25);
        card.add(usernameField, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(10,25,5,25);
        card.add(emailLabel, gbc);

        gbc.gridy = 5;
        gbc.insets = new Insets(0,25,10,25);
        card.add(emailField, gbc);

        gbc.gridy = 6;
        gbc.insets = new Insets(10,25,5,25);
        card.add(passLabel, gbc);

        gbc.gridy = 7;
        gbc.insets = new Insets(0,25,10,25);
        card.add(passwordField, gbc);

        gbc.gridy = 8;
        gbc.insets = new Insets(10,25,5,25);
        card.add(confirmLabel, gbc);

        gbc.gridy = 9;
        gbc.insets = new Insets(0,25,15,25);
        card.add(confirmPasswordField, gbc);
                gbc.gridy = 10;
        gbc.insets = new Insets(20,25,10,25);
        card.add(signupButton, gbc);

        gbc.gridy = 11;
        gbc.insets = new Insets(5,25,20,25);
        card.add(backButton, gbc);

        GridBagConstraints panelGbc = new GridBagConstraints();
        panelGbc.gridx = 0;
        panelGbc.gridy = 0;
        panelGbc.anchor = GridBagConstraints.CENTER;

        rightPanel.add(card, panelGbc);

        root.add(leftPanel);
        root.add(rightPanel);

        frame.add(root);

        frame.getRootPane().setDefaultButton(signupButton);

        usernameField.addActionListener(e ->
                emailField.requestFocusInWindow());

        emailField.addActionListener(e ->
                passwordField.requestFocusInWindow());

        passwordField.addActionListener(e ->
                confirmPasswordField.requestFocusInWindow());

        confirmPasswordField.addActionListener(e ->
                signupButton.doClick());

        //========================
        // SIGNUP
        //========================

        signupButton.addActionListener(e -> {

            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String password = String.valueOf(passwordField.getPassword());
            String confirm = String.valueOf(confirmPasswordField.getPassword());
                        if (username.isEmpty()
                    || email.isEmpty()
                    || password.isEmpty()
                    || confirm.isEmpty()) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Please fill all fields.",
                        "Validation",
                        JOptionPane.WARNING_MESSAGE);

                return;
            }

            if (username.length() < 3) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Username must contain at least 3 characters.");

                return;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Please enter a valid email address.");

                return;
            }

            if (password.length() < 6) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Password must be at least 6 characters.");

                return;
            }

            if (!password.equals(confirm)) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Passwords do not match.");

                passwordField.setText("");
                confirmPasswordField.setText("");

                passwordField.requestFocusInWindow();

                return;
            }

            User user = new User();

            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);

            UserDAO dao = new UserDAO();

            if (dao.registerUser(user)) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Account Created Successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                frame.dispose();

                new LoginPage();

            } else {

                JOptionPane.showMessageDialog(
                        frame,
                        "Signup Failed!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        });
        // ========================
        // BACK BUTTON
        // ========================

        backButton.addActionListener(e -> {

            frame.dispose();

            new LoginPage();

        });

        SwingUtilities.invokeLater(() -> usernameField.requestFocusInWindow());

        frame.setVisible(true);

    }

}