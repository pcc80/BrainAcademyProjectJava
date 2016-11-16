package GUI;

import DataBase.ConnectingToDataBase;
import DataBase.PricesListTableModel;
import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewPrices {
    
    public void ViewFlighhts() throws SQLException {
        PricesListTableModel prltm = new PricesListTableModel();
        ConnectingToDataBase connection = new ConnectingToDataBase();
        JDialog viewModalPane = new JDialog();
        viewModalPane.setModal(true);    
        viewModalPane.setTitle("Prices list...");
        viewModalPane.setBounds(100, 200, 600, 300);
        prltm.addData(connection);
        JTable ViewFlightsResultTable = new JTable(prltm);
        JScrollPane viewScrolPane = new JScrollPane(ViewFlightsResultTable);
        viewModalPane.add(viewScrolPane);
        viewModalPane.setVisible(true);
    }
}