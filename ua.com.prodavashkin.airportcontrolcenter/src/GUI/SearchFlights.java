package GUI;

import DataBase.ConnectingToDataBase;
import DataBase.FlightsListTableModel;
import DataBase.UpdateDB;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class SearchFlights {

    private String query;
    private String id;
    private String updateText;
    private String arg; // argument for SQL query
    private int numberCellFromArray;
    private String [] flightStatusItemsArray;
    private String number;
    
    public void SearchFlights (String arg) throws SQLException {

        ImageIcon icon = new ImageIcon("images/question.png", "?"); 
                
        ConnectingToDataBase conn = new ConnectingToDataBase();
        conn.connect();
        
        if (Main.Main.getAccessLevel() != 3) {
            query = "SELECT flight_status FROM flight_status";
            flightStatusItemsArray = new String [conn.GetCounter(query)];
            conn.rs = conn.stmt.executeQuery(query);
            int i = 0;
            while (conn.rs.next()) {
                flightStatusItemsArray[i++] = conn.rs.getString("flight_status");
            }
        }
        
        FlightsListTableModel fltm = new FlightsListTableModel();

        JDialog searchModalPane = new JDialog();
        searchModalPane.setModal(true);    
        searchModalPane.setTitle("Result Search...");
        searchModalPane.setBounds(100, 200, 600, 300);

        boolean temp = fltm.addData(conn, arg);

        JTable searchFlightsResultTable = new JTable(fltm);
        
        if (Main.Main.getAccessLevel() != 3) {
            searchFlightsResultTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e)
                {

                    String temp ;
                    if (e.getClickCount() == 2)
                    {
                        Point p = e.getPoint();
                        int row = searchFlightsResultTable.rowAtPoint(p);
                        int column = searchFlightsResultTable.columnAtPoint(p); 
                        id = (String) searchFlightsResultTable.getValueAt(row, 0);
                        number = (String) searchFlightsResultTable.getValueAt(row, 1);
                        temp = (String)JOptionPane.showInputDialog(
                                null,
                                "Edit Flight Status :", "Status Selector",
                                JOptionPane.QUESTION_MESSAGE,
                                icon,
                                flightStatusItemsArray,
                                (Object) searchFlightsResultTable.getValueAt(row, 4));
                        numberCellFromArray = Arrays.asList(flightStatusItemsArray).indexOf(temp); 
                        query = "UPDATE flight_number SET flight_status = \""+(numberCellFromArray+1)+"\" WHERE id = "+id+"";
                        updateText = (String) searchFlightsResultTable.getValueAt(row, 2);
                        try {
                            UpdateDB update = new UpdateDB(query);
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Thomething wrong", "WARNING", JOptionPane.ERROR_MESSAGE);
                        }
                        searchModalPane.dispose();
                        SearchFlights sf = new SearchFlights();
                        try {
                            sf.SearchFlights(number);
                        } catch (SQLException ex) {
                             JOptionPane.showMessageDialog(null, "Thomething wrong", "WARNING", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                }
            });
        }
        JScrollPane searchScrolPane = new JScrollPane(searchFlightsResultTable);
        searchModalPane.add(searchScrolPane);

        if (temp == false) {
            JOptionPane.showMessageDialog(null, "Your have no match in the database !", "WRONG", JOptionPane.ERROR_MESSAGE);
        } else {
            searchModalPane.setVisible(true);
        }
    }
}
    