package GUI;

import DataBase.ConnectingToDataBase;
import DataBase.PassangerListTableModel;

import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;



public class ViewPassanger {
    
    public void ViewPassanger() throws SQLException {

        PassangerListTableModel pltm = new PassangerListTableModel();
        ConnectingToDataBase connection = new ConnectingToDataBase();

        JDialog viewModalPane = new JDialog();
        viewModalPane.setModal(true);    
        viewModalPane.setTitle("Passanger list...");
        viewModalPane.setBounds(100, 200, 900, 300);

        pltm.addData(connection);

        JTable ViewPassangerResultTable = new JTable(pltm);
        JScrollPane viewScrolPane = new JScrollPane(ViewPassangerResultTable);
        viewModalPane.add(viewScrolPane);
        viewModalPane.setVisible(true);
    }
}