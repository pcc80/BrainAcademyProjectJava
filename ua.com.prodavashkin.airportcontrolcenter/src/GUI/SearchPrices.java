package GUI;

import DataBase.ConnectingToDataBase;
import DataBase.PricesListTableModel;
import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SearchPrices {
    
    public void SearchFlights (String arg) throws SQLException {
        PricesListTableModel prltm = new PricesListTableModel();
        ConnectingToDataBase connection = new ConnectingToDataBase();
        
        JDialog searchModalPane = new JDialog();
        searchModalPane.setModal(true);    
        searchModalPane.setTitle("Result Search...");
        searchModalPane.setBounds(100, 200, 600, 300);
        
        boolean temp = prltm.addData(connection, arg);
        JTable searchFlightsResultTable = new JTable(prltm);
//        searchPassangerResultTable.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e)
//            {
//                if (e.getClickCount() == 2)
//                {
//                    Point p = e.getPoint();
//                    int row = searchPassangerResultTable.rowAtPoint(p);
//                    int column = searchPassangerResultTable.columnAtPoint(p); 
//                    System.out.println(searchPassangerResultTable.getValueAt(row, column));
//                    if (column>0 & column<=6) {
//                        JOptionPane.showMessageDialog(null, "You must get information about passanger", "Passanger Information", JOptionPane.INFORMATION_MESSAGE);
//                    } else if (column == 7 | column == 8) {
//                        JOptionPane.showMessageDialog(null, "You must get information about flight", "Flight Information", JOptionPane.INFORMATION_MESSAGE);
//                    }
//                }
//            }
//
//        });
//        
        JScrollPane searchScrolPane = new JScrollPane(searchFlightsResultTable);
        
        searchModalPane.add(searchScrolPane);
        if (temp == false) {
            JOptionPane.showMessageDialog(null, "Your have no match in the database !", "WRONG", JOptionPane.ERROR_MESSAGE);
        } else {
            searchModalPane.setVisible(true);
        }
              
    }
}
