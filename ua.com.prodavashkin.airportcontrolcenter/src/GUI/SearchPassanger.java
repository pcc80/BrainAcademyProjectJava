package GUI;

import DataBase.PassangerListTableModel;
import DataBase.ConnectingToDataBase;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        
        pltm.addData(connection, arg);
        JTable searchPassangerResultTable = new JTable(pltm);
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
        
        JScrollPane searchScrolPane = new JScrollPane(searchPassangerResultTable);
        
        searchModalPane.add(searchScrolPane);
        searchModalPane.setVisible(true);
              
    }
}
