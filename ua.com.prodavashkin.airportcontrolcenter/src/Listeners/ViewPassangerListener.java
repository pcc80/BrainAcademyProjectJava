package Listeners;

import GUI.ViewPassanger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewPassangerListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        ViewPassanger vp = new ViewPassanger();
        try {
            vp.ViewPassanger();
        } catch (SQLException ex) {
            Logger.getLogger(ViewPassangerListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
