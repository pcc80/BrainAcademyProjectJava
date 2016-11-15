package Listeners;

import GUI.ViewPrices;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ViewPricesListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        ViewPrices vp = new ViewPrices();
        try {
            vp.ViewFlighhts();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Something wrong !", "WRONG", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
