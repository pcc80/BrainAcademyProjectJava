package Listeners;

import Export.ExportPassangersDataToExcel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JOptionPane;

public class ExportPassangersListToExcelListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        ExportPassangersDataToExcel export = new ExportPassangersDataToExcel();
        try {
            export.exportToExcel();
        } catch (ParseException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Somthing wrong !", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
