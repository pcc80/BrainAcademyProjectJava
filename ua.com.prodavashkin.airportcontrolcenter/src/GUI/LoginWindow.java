package GUI;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import DataBase.Logining;



public final class LoginWindow {

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
        frame.getContentPane().setLayout(new CardLayout(0,0));

        loginPanel = new JPanel();
        frame.add(loginPanel, "login");
        loginPanel.setLayout(null);

        JLabel appTitleLabel = new JLabel("AirPort Control Center ("+Main.Main.getVersionApp()+")");
        appTitleLabel.setBounds(20, 2, 200, 15);
        loginPanel.add(appTitleLabel);

        JLabel loginJLabel = new JLabel("Your Login :");
        loginJLabel.setBounds(15, 30, 80, 20);
        loginPanel.add(loginJLabel);

        JTextField loginTextField = new JTextField();
        loginTextField.setBounds(120, 30, 100, 25);
        loginPanel.add(loginTextField);

        JLabel passwordJLabel = new JLabel("Your Password :");
        passwordJLabel.setBounds(15, 70, 100, 25);
        loginPanel.add(passwordJLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 100, 25);
        loginPanel.add(passwordField);

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
                    Logger.getLogger(LoginWindow.class.getName()).log(Level.SEVERE, null, ex);
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

        loginButton.setBounds(75, 110, 100, 30);
        loginPanel.add(loginButton);
    }

}