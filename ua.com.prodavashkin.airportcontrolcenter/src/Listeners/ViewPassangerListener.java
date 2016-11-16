package Listeners;

import GUI.ViewPassanger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ViewPassangerListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        ViewPassanger vp = new ViewPassanger();
        try {
            vp.ViewPassanger();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Something wrong !", "WRONG", JOptionPane.ERROR_MESSAGE);
        }
    }
}