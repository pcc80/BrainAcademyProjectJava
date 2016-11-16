package DataBase;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public final class PricesListTableModel extends AbstractTableModel{
    private final int columnCount = 5;
    private final ArrayList<String[]> pricesArrayList;
    
    public PricesListTableModel(){
        pricesArrayList = new ArrayList<>();
        for (int i =0; i < pricesArrayList.size(); i++) {
            pricesArrayList.add(new String[getColumnCount()]);
        }
    }
    
    @Override
    public int getRowCount() {
        return pricesArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }
    
    @Override
    public String getColumnName(int ColumnIndex) {
        switch (ColumnIndex) {
            case 0: return "# Id";
            case 1: return "Price, $";
            case 2: return "Flight Number";
            case 3: return "Arrived Port";
            case 4: return "Departured Port";
        }
        return "";
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = pricesArrayList.get(rowIndex);
        return rows[columnIndex];
    }
    
    public void addData (String [] row) {
        String[] rowPassanger = new String[getColumnCount()];
        rowPassanger = row;
        pricesArrayList.add(rowPassanger);
    }
    
    public boolean addData (ConnectingToDataBase connection, String queryOption) throws SQLException {
        System.out.println(queryOption);
        boolean refer = true;
        connection.connect();
        connection.rs = connection.stmt.executeQuery(
                "SELECT fn.id, fn.flight_number, p.port, p2.port AS p2, fn.price "
                + "FROM flight_number AS fn "
                + "INNER JOIN port AS p ON fn.port_arrived = p.id "
                + "INNER JOIN port2 AS p2 ON fn.port_departured = p2.id "
                + "INNER JOIN flight_status AS fs ON fn.flight_status = fs.id "
                + "WHERE fn.price LIKE \"%" + queryOption + "%\"");
        
        while (connection.rs.next()) {
            String[] rowPassanger ={
                connection.rs.getString("id"),
                connection.rs.getString("price"),
                connection.rs.getString("flight_number"),
                connection.rs.getString("port"),
                connection.rs.getString("p2")
            };
            addData(rowPassanger);
        }
        if (pricesArrayList.isEmpty() == true) {connection.logout(); refer = false;} 
        connection.logout();
        return refer;
    } 
    
    public void addData (ConnectingToDataBase connection) throws SQLException {
        connection.connect();
        connection.rs = connection.stmt.executeQuery(
                "SELECT fn.id, fn.flight_number, p.port, p2.port AS p2, fn.price " +
                "FROM flight_number AS fn " +
                "INNER JOIN port AS p ON fn.port_arrived = p.id " +
                "INNER JOIN port2 AS p2 ON fn.port_departured = p2.id " +
                "INNER JOIN flight_status AS fs ON fn.flight_status = fs.id");
        while (connection.rs.next()) {
            String[] rowPassanger ={
                connection.rs.getString("id"),
                connection.rs.getString("price"),
                connection.rs.getString("flight_number"),
                connection.rs.getString("port"),
                connection.rs.getString("p2")
            };
            addData(rowPassanger);
        }
        connection.logout();
    } 
}