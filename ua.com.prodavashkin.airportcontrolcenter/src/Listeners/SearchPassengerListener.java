package Listeners;

import GUI.SearchPassanger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SearchPassengerListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        String searchText = JOptionPane.showInputDialog("Input: (<F.Name> or <L.Name> or <Passport number> :");
        if (searchText == null) {}
        else {
            searchText.trim();
            if (searchText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Your search text is empty !", "WRONG", JOptionPane.ERROR_MESSAGE);
            } else {
                SearchPassanger sp = new SearchPassanger();
                try {
                    sp.searchPassanger(searchText);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Soomthing wrong !", "WRONG", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
