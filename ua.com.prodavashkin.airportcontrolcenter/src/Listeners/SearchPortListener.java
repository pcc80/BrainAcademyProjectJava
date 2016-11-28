package Listeners;

import GUI.SearchPort;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class SearchPortListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {

        String searchText = JOptionPane.showInputDialog("Input: <Port> :");
        if (searchText == null) {}
        else {
            searchText.trim();
            if (searchText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Your search text is empty !", "WRONG", JOptionPane.ERROR_MESSAGE);
            } else {
                SearchPort sp = new SearchPort();
                try {
                    sp.SearchPort(searchText);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Soomthing wrong !", "WRONG", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

}