package GUI;

import DataBase.PassangerListTableModel;
import DataBase.ConnectingToDataBase;
import DataBase.UpdateDB;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;




public class SearchPassanger {
    
    private String query;
    private String id;
    private String updateText;
    
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
                    switch (column) {
                        case 1:
                            id = (String) searchPassangerResultTable.getValueAt(row, 0);
                            updateText = JOptionPane.showInputDialog(null, "Edit First name :", searchPassangerResultTable.getValueAt(row, column));
                            query = "UPDATE passangers SET first_name = \""+updateText+"\" WHERE id = "+id+"";
                            break;
                        case 2:
                            id = (String) searchPassangerResultTable.getValueAt(row, 0);
                            updateText = JOptionPane.showInputDialog(null, "Edit Last name :", searchPassangerResultTable.getValueAt(row, column));
                            query = "UPDATE passangers SET last_name = \""+updateText+"\" WHERE id = "+id+"";
                            break;
                        case 3:
                            id = (String) searchPassangerResultTable.getValueAt(row, 0);
                            updateText = JOptionPane.showInputDialog(null, "Edit Sex :", searchPassangerResultTable.getValueAt(row, column));
                            query = "UPDATE passangers SET sex = \""+updateText+"\" WHERE id = "+id+"";
                            break;
                        case 5:
                            id = (String) searchPassangerResultTable.getValueAt(row, 0);
                            updateText = JOptionPane.showInputDialog(null, "Edit Nationality :", searchPassangerResultTable.getValueAt(row, column));
                            query = "UPDATE passangers SET nationality = \""+updateText+"\" WHERE id = "+id+"";
                            break;
                        case 6:
                            id = (String) searchPassangerResultTable.getValueAt(row, 0);
                            updateText = JOptionPane.showInputDialog(null, "Edit Passport :", searchPassangerResultTable.getValueAt(row, column));
                            query = "UPDATE passangers SET passport = \""+updateText+"\" WHERE id = "+id+"";
                            break;
                        case 4:
                            id = (String) searchPassangerResultTable.getValueAt(row, 0);
                            updateText = JOptionPane.showInputDialog(null, "Edit Birthday :", searchPassangerResultTable.getValueAt(row, column));
                            query = "UPDATE passangers SET birthday = \""+updateText+"\" WHERE id = "+id+"";
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