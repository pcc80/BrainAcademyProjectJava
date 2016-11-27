package GUI;

import DataBase.PassangerListTableModel;
import DataBase.ConnectingToDataBase;
import DataBase.UpdateDB;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;




public class SearchPassanger {
    
    private String query;
    private String id;
    private String updateText;
    private String arg; // argument for SQL query
    
    public void searchPassanger (String arg) throws SQLException {

        PassangerListTableModel pltm = new PassangerListTableModel();
        ConnectingToDataBase connection = new ConnectingToDataBase();

        JDialog searchModalPane = new JDialog();
        searchModalPane.setModal(true);    
        searchModalPane.setTitle("Result Search...");
        searchModalPane.setBounds(100, 200, 900, 300);

        boolean temp = pltm.addData(connection, arg);

        JTable searchPassangerResultTable = new JTable(pltm);
        
        searchPassangerResultTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 2)
                {
                    Point p = e.getPoint();
                    int row = searchPassangerResultTable.rowAtPoint(p);
                    int column = searchPassangerResultTable.columnAtPoint(p); 
                    id = (String) searchPassangerResultTable.getValueAt(row, 0);
                    switch (column) {
                        case 1:
                            updateText = JOptionPane.showInputDialog(null, "Edit First name :", searchPassangerResultTable.getValueAt(row, column));
                            updateText.trim();
                            query = "UPDATE passangers SET first_name = \""+updateText+"\" WHERE id = "+id+"";
                            updateText = (String) searchPassangerResultTable.getValueAt(row, 2);
                            break;
                        case 2:
                            updateText = JOptionPane.showInputDialog(null, "Edit Last name :", searchPassangerResultTable.getValueAt(row, column));
                            updateText.trim();
                            query = "UPDATE passangers SET last_name = \""+updateText+"\" WHERE id = "+id+"";
                            break;
                        case 3:
                            updateText = JOptionPane.showInputDialog(null, "Edit Sex :", searchPassangerResultTable.getValueAt(row, column));
                            updateText.trim();
                            query = "UPDATE passangers SET sex = \""+updateText+"\" WHERE id = "+id+"";
                            updateText = (String) searchPassangerResultTable.getValueAt(row, 2);
                            break;
                        case 4:
                            updateText = JOptionPane.showInputDialog(null, "Edit Birthday :", searchPassangerResultTable.getValueAt(row, column));
                            updateText.trim();
                            query = "UPDATE passangers SET date_of_birthday = \""+updateText+"\" WHERE id = "+id+"";
                            updateText = (String) searchPassangerResultTable.getValueAt(row, 2);
                            break;
                        case 5:
                            updateText = JOptionPane.showInputDialog(null, "Edit Nationality :", searchPassangerResultTable.getValueAt(row, column));
                            updateText.trim();
                            query = "UPDATE passangers SET nationality = \""+updateText+"\" WHERE id = "+id+"";
                            updateText = (String) searchPassangerResultTable.getValueAt(row, 2);
                            break;
                        case 6:
                            updateText = JOptionPane.showInputDialog(null, "Edit Passport :", searchPassangerResultTable.getValueAt(row, column));
                            updateText.trim();
                            query = "UPDATE passangers SET passport = \""+updateText+"\" WHERE id = "+id+"";
                            updateText = (String) searchPassangerResultTable.getValueAt(row, 2);
                            break;
                        case 7:
                            break;
                        case 8:
                            break;
                        default:
                            break;
                    }
                    try {
                        UpdateDB update = new UpdateDB(query);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Thomething wrong", "WARNING", JOptionPane.ERROR_MESSAGE);
                    }
                
                    searchModalPane.dispose();
                    SearchPassanger sp = new SearchPassanger();
                    try {
                        sp.searchPassanger(updateText);
                    } catch (SQLException ex) {
                         JOptionPane.showMessageDialog(null, "Thomething wrong", "WARNING", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        });

        JScrollPane searchScrolPane = new JScrollPane(searchPassangerResultTable);
        searchModalPane.add(searchScrolPane);

        if (temp == false) {
            JOptionPane.showMessageDialog(null, "Your have no match in the database !", "WRONG", JOptionPane.ERROR_MESSAGE);
        } else {
            searchModalPane.setVisible(true);
        }
    }

}