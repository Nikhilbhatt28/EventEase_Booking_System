    package ui;

    import dao.UserDAO;
    import model.User;
    import ui.components.*;

    import javax.swing.*;
    import javax.swing.border.EmptyBorder;
    import java.awt.*;

    public class LoginPage {

        private JFrame frame;

        private RoundedTextField usernameField;
        private RoundedPasswordField passwordField;

        private RoundedButton loginButton;
        private RoundedButton signupButton;

        public LoginPage() {

            AppTheme.apply();

            frame = new JFrame("EventEase");

            frame.setSize(950,600);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel root = new JPanel(new GridLayout(1,2));

            //======================
            // LEFT PANEL
            //======================

            JPanel leftPanel = new JPanel();

            leftPanel.setBackground(AppColors.PRIMARY);

            leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));

            leftPanel.setBorder(new EmptyBorder(80,50,80,50));

            JLabel logo = new JLabel("EVENTEASE");

            logo.setAlignmentX(Component.CENTER_ALIGNMENT);

            logo.setFont(new Font("Segoe UI",Font.BOLD,36));

            logo.setForeground(Color.WHITE);

            JLabel subTitle = new JLabel("Smart Event Booking System");

            subTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

            subTitle.setFont(new Font("Segoe UI",Font.PLAIN,18));

            subTitle.setForeground(new Color(230,230,230));

            JLabel line1 = new JLabel("✔ Secure Booking");

            JLabel line2 = new JLabel("✔ Fast Performance");

            JLabel line3 = new JLabel("✔ Modern Interface");

            line1.setAlignmentX(Component.CENTER_ALIGNMENT);
            line2.setAlignmentX(Component.CENTER_ALIGNMENT);
            line3.setAlignmentX(Component.CENTER_ALIGNMENT);

            line1.setForeground(Color.WHITE);
            line2.setForeground(Color.WHITE);
            line3.setForeground(Color.WHITE);

            line1.setFont(AppFonts.TEXT);
            line2.setFont(AppFonts.TEXT);
            line3.setFont(AppFonts.TEXT);

            leftPanel.add(Box.createVerticalGlue());

            leftPanel.add(logo);

            leftPanel.add(Box.createRigidArea(new Dimension(0,15)));

            leftPanel.add(subTitle);

            leftPanel.add(Box.createRigidArea(new Dimension(0,50)));

            leftPanel.add(line1);

            leftPanel.add(Box.createRigidArea(new Dimension(0,15)));

            leftPanel.add(line2);

            leftPanel.add(Box.createRigidArea(new Dimension(0,15)));

            leftPanel.add(line3);

            leftPanel.add(Box.createVerticalGlue());

            //======================
            // RIGHT PANEL
            //======================

            JPanel rightPanel = new JPanel(new GridBagLayout());

            rightPanel.setBackground(AppColors.BACKGROUND);

            ShadowPanel card = new ShadowPanel(30);

            card.setBackground(AppColors.CARD);

            card.setPreferredSize(new Dimension(360,430));

            card.setLayout(new BoxLayout(card,BoxLayout.Y_AXIS));

            card.setBorder(new EmptyBorder(35,35,35,35));

            JLabel welcome = new JLabel("Welcome Back");

            welcome.setFont(AppFonts.HEADING);

            welcome.setForeground(Color.WHITE);

            welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel info = new JLabel("Login to continue");

            info.setForeground(AppColors.TEXT_SECONDARY);

            info.setAlignmentX(Component.CENTER_ALIGNMENT);

            info.setFont(AppFonts.SUBTITLE);

            card.add(welcome);

            card.add(Box.createRigidArea(new Dimension(0,8)));

            card.add(info);

            card.add(Box.createRigidArea(new Dimension(0,30)));

            JLabel userLabel = new JLabel("Username");

            userLabel.setForeground(Color.WHITE);

            userLabel.setFont(AppFonts.LABEL);

            usernameField = new RoundedTextField(20);

            usernameField.setMaximumSize(new Dimension(280,45));

            usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            JLabel passLabel = new JLabel("Password");

            passLabel.setForeground(Color.WHITE);

            passLabel.setFont(AppFonts.LABEL);

            passwordField = new RoundedPasswordField(20);

            passwordField.setMaximumSize(new Dimension(280,45));
            passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);

            loginButton = new RoundedButton("LOGIN");

            signupButton = new RoundedButton("CREATE ACCOUNT");

            signupButton.setButtonColor(AppColors.SUCCESS);

            signupButton.setHoverColor(AppColors.SUCCESS_HOVER);

            loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            card.add(userLabel);

            card.add(Box.createRigidArea(new Dimension(0, 8)));

            card.add(usernameField);

            card.add(Box.createRigidArea(new Dimension(0, 18)));

            card.add(passLabel);


            card.add(Box.createRigidArea(new Dimension(0,8)));

            card.add(passwordField);

            card.add(Box.createRigidArea(new Dimension(0,30)));

            card.add(loginButton);

            card.add(Box.createRigidArea(new Dimension(0,15)));

            card.add(signupButton);
                    GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;

            rightPanel.add(card, gbc);

            root.add(leftPanel);
            root.add(rightPanel);

            frame.add(root);

            frame.getRootPane().setDefaultButton(loginButton);

            usernameField.addActionListener(e ->
                    passwordField.requestFocusInWindow());

            passwordField.addActionListener(e ->
                    loginButton.doClick());

            //========================
            // LOGIN
            //========================

            loginButton.addActionListener(e -> {

                String username = usernameField.getText().trim();

                String password = String.valueOf(
                        passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Please fill all fields.",
                            "Validation",
                            JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }

                UserDAO dao = new UserDAO();

                User user = dao.loginUser(username, password);

                if (user != null) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Login Successful!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    frame.dispose();

                    if (user.getUsername().equalsIgnoreCase("admin")) {

                        new AdminPage();

                    } else {

                        new HomePage(user.getId());

                    }

                } else {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Invalid Username or Password.",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE
                    );

                }

            });

            //========================
            // SIGNUP
            //========================

            signupButton.addActionListener(e -> {

                frame.dispose();

                new SignupPage();

            });

            SwingUtilities.invokeLater(() ->
                    usernameField.requestFocusInWindow());

            frame.setVisible(true);

        }
    }