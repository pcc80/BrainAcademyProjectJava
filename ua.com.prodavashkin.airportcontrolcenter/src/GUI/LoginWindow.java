package GUI;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import DataBase.Logining;
import javax.swing.GroupLayout;


public final class LoginWindow extends JFrame{

    private JFrame frame;
    private JPanel loginPanel;
    
    public void loginingWindows () {
        LoginWindow window = new LoginWindow();
        window.frame.setVisible(true);
    }
    
    public LoginWindow () {
        initialize();
    }
    
    public void initialize() {

        frame = new JFrame ();
        frame.setBounds(100, 100, 250, 200);
        frame.setTitle("LOGIN FORM");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane();

        loginPanel = new JPanel();
        GroupLayout jPanelLayout = new GroupLayout(loginPanel);
        loginPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
       
        JLabel appTitleLabel = new JLabel();
        appTitleLabel.setFont(appTitleLabel.getFont().deriveFont((float)13));
        appTitleLabel.setText("AirPort Control Center ("+Main.Main.getVersionApp()+")");
 
        JLabel loginJLabel = new JLabel();
        loginJLabel.setFont(loginJLabel.getFont().deriveFont((float)12));
        loginJLabel.setText("Your Login :");

        JLabel passwordJLabel = new JLabel();
        passwordJLabel.setFont(passwordJLabel.getFont().deriveFont((float)12));
        passwordJLabel.setText("Your Password :");

        JTextField loginTextField = new JTextField();
        
        JPasswordField passwordField = new JPasswordField();
        
        JButton loginButton = new JButton("LOG IN");
        loginButton.addActionListener((ActionEvent e) -> {
            String login = loginTextField.getText().trim();
            String password = passwordField.getText().trim();

            if (login.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Login or Password is empty!", "EMPTY", JOptionPane.WARNING_MESSAGE);
            } else {
                boolean logIn = false;
                Logining log = new Logining();

                try {
                    logIn = log.userVerification(login, password);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "I don't know what wrong!", "Error", JOptionPane.ERROR_MESSAGE);
                }

                if (logIn == true) {
                    frame.dispose();

                    MainWindow mainWindow = new MainWindow();
                    mainWindow.MainWindow();

                } else {
                    JOptionPane.showMessageDialog(null, "Incorect Login or Password!", "WRONG", JOptionPane.ERROR_MESSAGE);
                    loginTextField.setText(null);
                    passwordField.setText(null);
                }
            }
        });

        frame.add(loginPanel);
        
        GroupLayout layout = new GroupLayout(loginPanel);
        loginPanel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(appTitleLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(loginJLabel)
                            .addComponent(passwordJLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(loginTextField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(loginButton)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(appTitleLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(loginJLabel)
                    .addComponent(loginTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordJLabel)
                    .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(loginButton)
                .addContainerGap(55, Short.MAX_VALUE))
        );

    }

}