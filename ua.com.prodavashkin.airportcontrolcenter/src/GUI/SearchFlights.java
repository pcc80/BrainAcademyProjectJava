package GUI;

import DataBase.ConnectingToDataBase;
import DataBase.FlightsListTableModel;

import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;



public class SearchFlights {
    
    public void SearchFlights (String arg) throws SQLException {

        FlightsListTableModel fltm = new FlightsListTableModel();
        ConnectingToDataBase connection = new ConnectingToDataBase();

        JDialog searchModalPane = new JDialog();
        searchModalPane.setModal(true);    
        searchModalPane.setTitle("Result Search...");
        searchModalPane.setBounds(100, 200, 600, 300);

        boolean temp = fltm.addData(connection, arg);

        JTable searchFlightsResultTable = new JTable(fltm);
        JScrollPane searchScrolPane = new JScrollPane(searchFlightsResultTable);
        searchModalPane.add(searchScrolPane);

        if (temp == false) {
            JOptionPane.showMessageDialog(null, "Your have no match in the database !", "WRONG", JOptionPane.ERROR_MESSAGE);
        } else {
            searchModalPane.setVisible(true);
        }
    }

}