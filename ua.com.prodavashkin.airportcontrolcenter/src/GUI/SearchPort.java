package GUI;

import DataBase.PortListTableModel;
import DataBase.ConnectingToDataBase;

import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;




public class SearchPort {
    
    public void SearchPort (String arg) throws SQLException {

        PortListTableModel portltm = new PortListTableModel();
        ConnectingToDataBase connection = new ConnectingToDataBase();

        JDialog searchModalPane = new JDialog();
        searchModalPane.setModal(true);    
        searchModalPane.setTitle("Result Search...");
        searchModalPane.setBounds(100, 200, 600, 300);

        boolean temp = portltm.addData(connection, arg);

        JTable searchFlightsResultTable = new JTable(portltm);
        JScrollPane searchScrolPane = new JScrollPane(searchFlightsResultTable);
        searchModalPane.add(searchScrolPane);

        if (temp == false) {
            JOptionPane.showMessageDialog(null, "Your have no match in the database !", "WRONG", JOptionPane.ERROR_MESSAGE);
        } else {
            searchModalPane.setVisible(true);
        }
    }

}