package DataBase;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public final class FlightsListTableModel extends AbstractTableModel{

    private final int columnCount = 5;
    private final ArrayList<String[]> flightsArrayList;
    
    public FlightsListTableModel(){
        flightsArrayList = new ArrayList<>();
        for (int i =0; i < flightsArrayList.size(); i++) {
            flightsArrayList.add(new String[getColumnCount()]);
        }
    }
    
    @Override
    public int getRowCount() {
        return flightsArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }
    
    @Override
    public String getColumnName(int ColumnIndex) {
        switch (ColumnIndex) {
            case 0: return "# Id";
            case 1: return "Flight Number";
            case 2: return "Arrived Port";
            case 3: return "Departured Port";
            case 4: return "Flight Status";
        }
        return "";
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = flightsArrayList.get(rowIndex);
        return rows[columnIndex];
    }
    
    public void addData (String [] row) {
        String[] rowPassanger = new String[getColumnCount()];
        rowPassanger = row;
        flightsArrayList.add(rowPassanger);
    }
    
    public boolean addData (ConnectingToDataBase connection, String queryOption) throws SQLException {

        boolean refer = true;

        connection.connect();
        connection.rs = connection.stmt.executeQuery(
                "SELECT fn.id, fn.flight_number, p.port, p2.port AS p2, fs.flight_status "
                + "FROM flight_number AS fn "
                + "INNER JOIN port AS p ON fn.port_arrived = p.id "
                + "INNER JOIN port2 AS p2 ON fn.port_departured = p2.id "
                + "INNER JOIN flight_status AS fs ON fn.flight_status = fs.id "
                + "WHERE fn.flight_number LIKE \"%" + queryOption + "%\"");
        
        while (connection.rs.next()) {
            String[] rowPassanger ={
                connection.rs.getString("id"),
                connection.rs.getString("flight_number"),
                connection.rs.getString("port"),
                connection.rs.getString("p2"),
                connection.rs.getString("flight_status")
            };
            addData(rowPassanger);
        }
        if (flightsArrayList.isEmpty() == true) {connection.logout(); refer = false;} 
        connection.logout();
        return refer;
    } 
    
    public void addData (ConnectingToDataBase connection) throws SQLException {

        connection.connect();
        connection.rs = connection.stmt.executeQuery(
                "SELECT fn.id, fn.flight_number, p.port, p2.port AS p2, fs.flight_status " +
                "FROM flight_number AS fn " +
                "INNER JOIN port AS p ON fn.port_arrived = p.id " +
                "INNER JOIN port2 AS p2 ON fn.port_departured = p2.id " +
                "INNER JOIN flight_status AS fs ON fn.flight_status = fs.id");

        while (connection.rs.next()) {
            String[] rowPassanger ={
                connection.rs.getString("id"),
                connection.rs.getString("flight_number"),
                connection.rs.getString("port"),
                connection.rs.getString("p2"),
                connection.rs.getString("flight_status")
            };
            addData(rowPassanger);
        }
        connection.logout();
    } 

}