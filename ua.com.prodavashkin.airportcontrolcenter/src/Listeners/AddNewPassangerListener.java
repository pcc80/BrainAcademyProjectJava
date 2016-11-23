package Listeners;

import GUI.AddNewPassanger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class AddNewPassangerListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        AddNewPassanger anp = new AddNewPassanger();
        try {
            anp.AddNewPassanger();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Soomthing wrong !", "WRONG", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
