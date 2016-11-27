package GUI;

import DataBase.ConnectingToDataBase;
import DataBase.UpdateDB;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddNewPassanger extends JDialog {
    
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
        addWindow.setBounds(100, 220, 500, 350);
        addWindow.setResizable(false);
        addWindow.getContentPane();
        addWindow.add(contentPanel);
        
        GroupLayout layout = new GroupLayout(contentPanel);
        contentPanel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(firstNameLabel)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(passportLabel)
                        .addComponent(lastNameLabel)
                        .addComponent(nationalityLabel)
                        .addComponent(birthdayLabel)
                        .addComponent(sexLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(firstNameTextField)
                    .addComponent(lastNameTextField)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(yearBirthdayComboBox, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mounthBirthdayComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dayBirthdayComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(nationalityTextField)
                    .addComponent(passportTextField)
                    .addComponent(sexComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(flightNumberLabel)
                    .addComponent(flightNumberComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(salunClassLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(salunClassComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saveButton, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel)
                    .addComponent(firstNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(flightNumberLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lastNameLabel)
                    .addComponent(lastNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(flightNumberComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passportLabel)
                    .addComponent(passportTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(salunClassLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nationalityLabel)
                    .addComponent(nationalityTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(salunClassComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(birthdayLabel)
                    .addComponent(yearBirthdayComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(mounthBirthdayComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(dayBirthdayComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(sexLabel)
                    .addComponent(sexComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        contentPanel.add(firstNameLabel);
        contentPanel.add(firstNameTextField);
        contentPanel.add(lastNameLabel);
        contentPanel.add(lastNameTextField);
        contentPanel.add(passportLabel);
        contentPanel.add(passportTextField);
        contentPanel.add(nationalityLabel);
        contentPanel.add(nationalityTextField);
        contentPanel.add(birthdayLabel);
        contentPanel.add(yearBirthdayComboBox);
        contentPanel.add(mounthBirthdayComboBox);
        contentPanel.add(dayBirthdayComboBox);
        contentPanel.add(sexLabel);
        contentPanel.add(sexComboBox);
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
        contentPanel.add(flightNumberLabel);
        contentPanel.add(flightNumberComboBox);
        contentPanel.add(salunClassLabel);
        contentPanel.add(salunClassComboBox);
        
        contentPanel.add(saveButton);
        
                        
        addWindow.setVisible(true);

    }
    
}
