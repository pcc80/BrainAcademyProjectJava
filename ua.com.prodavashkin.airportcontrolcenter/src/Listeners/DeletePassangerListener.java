package Listeners;

import DataBase.DeletePassanger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DeletePassangerListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String parametr = JOptionPane.showInputDialog(null, "Please input ID passanger", "Delete passanger", JOptionPane.QUESTION_MESSAGE);
        if (parametr == null) {}
        else {
            parametr.trim();
            if (parametr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Your search text is empty !", "WRONG", JOptionPane.ERROR_MESSAGE);
            } else {
                DeletePassanger delete = new DeletePassanger ();
                try {
                    delete.DeletingFirstStep(parametr);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "4ABOTO HE TO :-8 !", "WRONG", JOptionPane.ERROR_MESSAGE);
                }

            }
        }    
    }
    
}
