package Listeners;

import Export.ExportFlightsDataToExcel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JOptionPane;

public class ExportFlightsListToExcelListener implements ActionListener{
    
    @Override
    public void actionPerformed(ActionEvent e) {
        ExportFlightsDataToExcel export = new ExportFlightsDataToExcel();
        try {
            export.exportToExcel();
        } catch (ParseException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Somthing wrong !", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
