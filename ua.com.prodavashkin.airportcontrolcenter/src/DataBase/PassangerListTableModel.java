package DataBase;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public final class PassangerListTableModel extends AbstractTableModel{

    private final int columnCount = 9;
    private final ArrayList<String[]> passangersArrayList;
    
    public PassangerListTableModel(){
        passangersArrayList = new ArrayList<>();
        for (int i =0; i < passangersArrayList.size(); i++) {
            passangersArrayList.add(new String[getColumnCount()]);
        }
    }
    
    @Override
    public int getRowCount() {
        return passangersArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }
    
    @Override
    public String getColumnName(int ColumnIndex) {
        switch (ColumnIndex) {
            case 0: return "# Id";
            case 1: return "First Name";
            case 2: return "Last Name";
            case 3: return "Sex";
            case 4: return "Birthday";
            case 5: return "Nationality";
            case 6: return "Passport";
            case 7: return "Flyght Number";
            case 8: return "Salun Class";
        }
        return "";
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = passangersArrayList.get(rowIndex);
        return rows[columnIndex];
    }
    
    
    public void addData (String [] row) {
        String[] rowPassanger = new String[getColumnCount()];
        rowPassanger = row;
        passangersArrayList.add(rowPassanger);
    }
    
    public boolean addData (ConnectingToDataBase connection, String queryOption) throws SQLException {
        boolean refer = true;
        connection.connect();
        connection.rs = connection.stmt.executeQuery(
                "SELECT p.id, p.first_name, p.last_name, s.sex, p.date_of_birthday, p.nationality, p.passport, f.flight_number, salun.salun_type_full "
                + "FROM passangers AS p "
                + "INNER JOIN sex AS s ON p.sex = s.id "
                + "INNER JOIN flight_number AS f ON p.flyght_number = f.id "
                + "INNER JOIN salun_class AS salun ON p.salun_class = salun.id "
                + "where p.first_name LIKE \""+queryOption+"%\" or p.last_name LIKE \""+queryOption+"%\" or p.passport LIKE \""+queryOption+"%\" ");
        
        while (connection.rs.next()) {
            String[] rowPassanger ={
                connection.rs.getString("id"),
                connection.rs.getString("first_name"),
                connection.rs.getString("last_name"),
                connection.rs.getString("sex"),
                connection.rs.getString("date_of_birthday"),
                connection.rs.getString("nationality"),
                connection.rs.getString("passport"),
                connection.rs.getString("flight_number"),
                connection.rs.getString("salun_type_full")
            };
            addData(rowPassanger);
        }
        if (passangersArrayList.isEmpty() == true) {refer = false;} 
        connection.logout();
        return refer;
    } 
    
    public void addData (ConnectingToDataBase connection) throws SQLException {
        connection.connect();
        connection.rs = connection.stmt.executeQuery(
                "SELECT p.id, p.first_name, p.last_name, s.sex, p.date_of_birthday, p.nationality, p.passport, f.flight_number, salun.salun_type_full "
                + "FROM passangers AS p "
                + "INNER JOIN sex AS s ON p.sex = s.id "
                + "INNER JOIN flight_number AS f ON p.flyght_number = f.id "
                + "INNER JOIN salun_class AS salun ON p.salun_class = salun.id");
        while (connection.rs.next()) {
            String[] rowPassanger ={
                connection.rs.getString("id"),
                connection.rs.getString("first_name"),
                connection.rs.getString("last_name"),
                connection.rs.getString("sex"),
                connection.rs.getString("date_of_birthday"),
                connection.rs.getString("nationality"),
                connection.rs.getString("passport"),
                connection.rs.getString("flight_number"),
                connection.rs.getString("salun_type_full")
            };
            addData(rowPassanger);
        }
        connection.logout();
    } 
}
