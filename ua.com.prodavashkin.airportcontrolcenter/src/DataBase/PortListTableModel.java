package DataBase;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public final class PortListTableModel extends AbstractTableModel{
    private final int columnCount = 5;
    private final ArrayList<String[]> portArrayList;
    
    public PortListTableModel(){
        portArrayList = new ArrayList<>();
        for (int i =0; i < portArrayList.size(); i++) {
            portArrayList.add(new String[getColumnCount()]);
        }
    }
    
    @Override
    public int getRowCount() {
        return portArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }
    
    @Override
    public String getColumnName(int ColumnIndex) {
        switch (ColumnIndex) {
            case 0: return "# Id";
            case 1: return "Port";
            case 2: return "Code";
            case 3: return "City";
            case 4: return "Country";
        }
        return "";
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = portArrayList.get(rowIndex);
        return rows[columnIndex];
    }
    
    public void addData (String [] row) {
        String[] rowPassanger = new String[getColumnCount()];
        rowPassanger = row;
        portArrayList.add(rowPassanger);
    }
    
    public boolean addData (ConnectingToDataBase connection, String queryOption) throws SQLException {
        boolean refer = true;
        connection.connect();
        connection.rs = connection.stmt.executeQuery(
                "SELECT p.id, p.port, p.code, c.city, co.country "
                + "FROM port AS p "
                + "INNER JOIN city AS c ON p.city = c.id "
                + "INNER JOIN country AS co ON c.country = co.id "
                + "WHERE p.port LIKE \"%" + queryOption + "%\" "
                    + "OR p.code LIKE \"%" + queryOption + "%\" "
                    + "OR c.city LIKE \"%" + queryOption + "%\" "
                    + "OR co.country LIKE \"%" + queryOption + "%\" ");
        
        while (connection.rs.next()) {
            String[] rowPassanger ={
                connection.rs.getString("id"),
                connection.rs.getString("port"),
                connection.rs.getString("code"),
                connection.rs.getString("city"),
                connection.rs.getString("country")
            };
            addData(rowPassanger);
        }
        if (portArrayList.isEmpty() == true) {connection.logout(); refer = false;} 
        connection.logout();
        return refer;
    } 
}