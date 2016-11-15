package Listeners;

import GUI.SearchFlights;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class SearchFlightsListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        String searchText = JOptionPane.showInputDialog("Input: <Flight number> :");
        if (searchText == null) {}
        else {
            searchText.trim();
            if (searchText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Your search text is empty !", "WRONG", JOptionPane.ERROR_MESSAGE);
            } else {
                SearchFlights sf = new SearchFlights();
                try {
                    sf.SearchFlights(searchText);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Soomthing wrong !", "WRONG", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

}
