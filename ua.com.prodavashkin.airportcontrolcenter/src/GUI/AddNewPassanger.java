package GUI;

import DataBase.ConnectingToDataBase;
import DataBase.UpdateDB;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddNewPassanger {
    
    private String query;
    private String idFlightNumber;
    private String idSex;
    private String idSalunClass;
    
    public void AddNewPassanger () throws SQLException {
        
        ConnectingToDataBase conn = new ConnectingToDataBase();
        conn.connect();
        
        query = "SELECT sex FROM sex";
        String [] sexItemsArray = new String [conn.GetCounter(query)];
        conn.rs = conn.stmt.executeQuery(query);
        int i = 0;
        while (conn.rs.next()) {
            sexItemsArray[i++] = conn.rs.getString("sex");
        }

        query = "SELECT flight_number FROM flight_number";
        String [] flightNumberItemsArray = new String [conn.GetCounter(query)];
        conn.rs = conn.stmt.executeQuery(query);
        i = 0;
        while (conn.rs.next()) {
            flightNumberItemsArray[i++] = conn.rs.getString("flight_number");
        }

        query = "SELECT salun_type_full FROM salun_class";
        String [] flightStatusItemsArray = new String [conn.GetCounter(query)];
        conn.rs = conn.stmt.executeQuery(query);
        i = 0;
        while (conn.rs.next()) {
            flightStatusItemsArray[i++] = conn.rs.getString("salun_type_full");
        }
        conn.logout();
        
        
        JDialog addWindow = new JDialog();
        JPanel contentPanel = new JPanel(null);
        JButton saveButton = new JButton("Save");
        JLabel firstNameLabel = new JLabel("First Name :");
        JLabel lastNameLabel = new JLabel("Last Name :");
        JLabel passportLabel = new JLabel("Passport :");
        JLabel nationalityLabel = new JLabel("Nationality :");
        JLabel birthdayLabel = new JLabel("Birthday :");
        JLabel sexLabel = new JLabel("Sex :");
        JLabel flightNumberLabel = new JLabel("Flight Number :");
        JLabel salunClassLabel = new JLabel("Salun Class :");
        JComboBox sexComboBox = new JComboBox();
        JComboBox flightNumberComboBox = new JComboBox();
        JComboBox salunClassComboBox = new JComboBox();
        JTextField firstNameTextField = new JTextField();
        JTextField lastNameTextField = new JTextField();
        JTextField passportTextField = new JTextField();
        JTextField nationalityTextField = new JTextField();
        JComboBox dayBirthdayComboBox = new JComboBox();
        JComboBox mounthBirthdayComboBox = new JComboBox();
        JComboBox yearBirthdayComboBox = new JComboBox();
        
        for (String temp : sexItemsArray) {
            sexComboBox.addItem(temp);
        }
        
        for (String temp : flightNumberItemsArray) {
            flightNumberComboBox.addItem(temp);
        }
        
        for (String temp : flightStatusItemsArray) {
            salunClassComboBox.addItem(temp);
        }
        
        for (i = 1951; i < 2016; i++) {
            yearBirthdayComboBox.addItem(i);
        }
        
        for (i = 1; i < 13; i++) {
            mounthBirthdayComboBox.addItem(i);
        }
        
        for (i = 1; i < 32; i++) {
            dayBirthdayComboBox.addItem(i);
        }
        
        
        addWindow.setTitle("Add User");
        addWindow.setModal(true);
        addWindow.setBounds(100, 220, 500, 300);
        addWindow.setResizable(false);
        addWindow.getContentPane().setLayout(new CardLayout(0, 0));
        addWindow.add(contentPanel);
        
        
        contentPanel.add(firstNameLabel).setBounds(40, 45, 80, 25);
        contentPanel.add(firstNameTextField).setBounds(130, 45, 150, 25);
        contentPanel.add(lastNameLabel).setBounds(40, 75, 80, 25);
        contentPanel.add(lastNameTextField).setBounds(130, 75, 150, 25);
        contentPanel.add(passportLabel).setBounds(40, 105, 80, 25);
        contentPanel.add(passportTextField).setBounds(130, 105, 150, 25);
        contentPanel.add(nationalityLabel).setBounds(40, 135, 80, 25);
        contentPanel.add(nationalityTextField).setBounds(130, 135, 150, 25);
        contentPanel.add(birthdayLabel).setBounds(40, 165, 80, 25);
        contentPanel.add(yearBirthdayComboBox).setBounds(130, 165, 75, 25);
        contentPanel.add(mounthBirthdayComboBox).setBounds(210, 165, 50, 25);
        contentPanel.add(dayBirthdayComboBox).setBounds(265, 165, 50, 25);
        contentPanel.add(sexLabel).setBounds(40, 195, 80, 25);
        contentPanel.add(sexComboBox).setBounds(130, 195, 150, 25);
        saveButton.addActionListener((ActionEvent e) -> {
            if (firstNameTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "First Name area is empty !", "WRONG - EMPTY", JOptionPane.ERROR_MESSAGE);
            } else if (lastNameTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Last Name area is empty !", "WRONG - EMPTY", JOptionPane.ERROR_MESSAGE);
            } else if (passportTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Passport area is empty !", "WRONG - EMPTY", JOptionPane.ERROR_MESSAGE);
            } else if (nationalityTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nationality area is empty !", "WRONG - EMPTY", JOptionPane.ERROR_MESSAGE);
            } else {
                String [] rowIds = new String [3];
                ConnectingToDataBase connection = new ConnectingToDataBase();
                connection.connect();
                query = "SELECT flight_number.id AS id_number, sex.id AS id_sex, salun_class.id AS id_salun\n" +
                        "FROM flight_number \n" +
                        "INNER JOIN sex\n" +
                        "INNER JOIN salun_class\n" +
                        "WHERE flight_number = \""+flightNumberComboBox.getSelectedItem()+"\" "
                        + "AND sex = \""+sexComboBox.getSelectedItem()+"\" "
                        + "AND salun_type_full = \""+salunClassComboBox.getSelectedItem()+"\" ";
                try {
                    connection.rs = connection.stmt.executeQuery(query);
                    while (conn.rs.next()) {
                        idFlightNumber = conn.rs.getString("id_number");
                        idSex = conn.rs.getString("id_sex");
                        idSalunClass = conn.rs.getString("id_salun");
                    }
                    connection.logout();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Soomthing wrong !", "WRONG", JOptionPane.ERROR_MESSAGE);
                } finally {
                    try {
                        connection.logout();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Soomthing wrong !", "WRONG", JOptionPane.ERROR_MESSAGE);
                    }
                }
                
                
                query = "INSERT INTO passangers "
                        + "(flyght_number, first_name, last_name, nationality, passport, date_of_birthday, sex, salun_class) "
                        + "VALUES "
                        + "(\"" + idFlightNumber + "\", "
                        + "\"" + firstNameTextField.getText() + "\", "
                        + "\"" + lastNameTextField.getText() + "\", "
                        + "\"" + nationalityTextField.getText() + "\", "
                        + "\"" + passportTextField.getText() + "\", "
                        + "\"" + yearBirthdayComboBox.getSelectedItem() + "-" + mounthBirthdayComboBox.getSelectedItem() + "-"+ dayBirthdayComboBox.getSelectedItem() + "\", "
                        + "\"" + idSex + "\", "
                        + "\"" + idSalunClass + "\")";
                try {
                    UpdateDB update = new UpdateDB(query);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Soomthing wrong !", "WRONG", JOptionPane.ERROR_MESSAGE);
                }
                addWindow.dispose();
                JOptionPane.showMessageDialog(null, "New passanger added to DB !", "ADD USER", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        contentPanel.add(flightNumberLabel).setBounds(310, 45, 150, 25);
        contentPanel.add(flightNumberComboBox).setBounds(310, 75, 150, 25);
        contentPanel.add(salunClassLabel).setBounds(310, 105, 150, 25);
        contentPanel.add(salunClassComboBox).setBounds(310, 135, 150, 25);
        
        contentPanel.add(saveButton).setBounds(400, 230, 70, 25);
        
                        
        addWindow.setVisible(true);

    }
    
}
