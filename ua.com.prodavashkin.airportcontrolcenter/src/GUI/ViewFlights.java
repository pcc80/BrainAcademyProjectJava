package GUI;

import DataBase.ConnectingToDataBase;
import DataBase.FlightsListTableModel;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class ViewFlights {
    
    private Object [] flightNumberItemsArray;
    private int i;
    private String query;
    private String flightNumber;
    
    public void ViewFlighhts() throws SQLException {

        FlightsListTableModel fltm = new FlightsListTableModel();
        ConnectingToDataBase connection = new ConnectingToDataBase();
        connection.connect();

        JDialog viewModalPane = new JDialog();
        viewModalPane.setModal(true);    
        viewModalPane.setTitle("Flights list...");
        viewModalPane.setBounds(100, 200, 600, 300);

        fltm.addData(connection);

        JTable viewFlightsResultTable = new JTable(fltm);
        
        connection.logout();
            viewFlightsResultTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    if (e.getClickCount() == 2)
                    {
                        connection.connect();
                        Point p = e.getPoint();
                        int row = viewFlightsResultTable.rowAtPoint(p);
                        flightNumber = (String) viewFlightsResultTable.getValueAt(row, 1);
                        System.out.println(flightNumber);
                            query = "SELECT departure_date_and_time, arrive_date_and_time, terminal_departured, terminal_arrived, gate_departured, gate_arrived "
                                    + "FROM flight_information "
                                    + "INNER JOIN flight_number ON id_flight_number = flight_number.id "
                                    + "WHERE flight_number.flight_number = \"" + flightNumber + "\"";
                            flightNumberItemsArray = new String [6];
                            System.out.println(flightNumberItemsArray.length);
                        try {
                            connection.rs = connection.stmt.executeQuery(query);
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Woth's wrong", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                            i = 0;
                        try {
                            while (connection.rs.next()) {
                                flightNumberItemsArray[i++] = connection.rs.getString("departure_date_and_time");
                                flightNumberItemsArray[i++] = connection.rs.getString("terminal_departured");
                                flightNumberItemsArray[i++] = connection.rs.getString("gate_departured");
                                flightNumberItemsArray[i++] = connection.rs.getString("arrive_date_and_time");
                                flightNumberItemsArray[i++] = connection.rs.getString("terminal_arrived");
                                flightNumberItemsArray[i] = connection.rs.getString("gate_arrived");
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Woth's wrong", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        i = 0;
                        JOptionPane.showMessageDialog(null, 
                                "Departure Info: \n"
                                    + "  Date and time: \t" + flightNumberItemsArray[i++] + "\n" 
                                    + "  Terminal: \t" + flightNumberItemsArray[i++] + "\n" 
                                    + "  Gate: \t" + flightNumberItemsArray[i++] + "\n"
                                    + "Arrived Info: \n"
                                    + "  Date and time: \t" + flightNumberItemsArray[i++] + "\n" 
                                    + "  Terminal: \t" + flightNumberItemsArray[i++] + "\n" 
                                    + "  Gate: \t" + flightNumberItemsArray[i] + "\n", 
                                "Flight Info", 
                                JOptionPane.PLAIN_MESSAGE);

                    }    
                }    
            });
        
        JScrollPane viewScrolPane = new JScrollPane(viewFlightsResultTable);
        viewModalPane.add(viewScrolPane);
        viewModalPane.setVisible(true);
    }
}