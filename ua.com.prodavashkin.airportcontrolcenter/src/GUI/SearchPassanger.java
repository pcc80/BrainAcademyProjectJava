package GUI;

import DataBase.PassangerListTableModel;
import DataBase.ConnectingToDataBase;

import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;




public class SearchPassanger {
    
    public void searchPassanger (String arg) throws SQLException {

        PassangerListTableModel pltm = new PassangerListTableModel();
        ConnectingToDataBase connection = new ConnectingToDataBase();

        JDialog searchModalPane = new JDialog();
        searchModalPane.setModal(true);    
        searchModalPane.setTitle("Result Search...");
        searchModalPane.setBounds(100, 200, 900, 300);

        boolean temp = pltm.addData(connection, arg);

        JTable searchPassangerResultTable = new JTable(pltm);
        JScrollPane searchScrolPane = new JScrollPane(searchPassangerResultTable);
        searchModalPane.add(searchScrolPane);

        if (temp == false) {
            JOptionPane.showMessageDialog(null, "Your have no match in the database !", "WRONG", JOptionPane.ERROR_MESSAGE);
        } else {
            searchModalPane.setVisible(true);
        }
    }

}