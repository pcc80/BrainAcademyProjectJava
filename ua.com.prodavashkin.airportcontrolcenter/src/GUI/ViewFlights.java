package GUI;

import DataBase.ConnectingToDataBase;
import DataBase.FlightsListTableModel;
import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewFlights {
    
    public void ViewFlighhts() throws SQLException {
        FlightsListTableModel fltm = new FlightsListTableModel();
        ConnectingToDataBase connection = new ConnectingToDataBase();
        
        JDialog viewModalPane = new JDialog();
        viewModalPane.setModal(true);    
        viewModalPane.setTitle("Flights list...");
        viewModalPane.setBounds(100, 200, 600, 300);
        
        fltm.addData(connection);
        JTable ViewFlightsResultTable = new JTable(fltm);

        JScrollPane viewScrolPane = new JScrollPane(ViewFlightsResultTable);
        
        viewModalPane.add(viewScrolPane);
        viewModalPane.setVisible(true);
              
    }
}
