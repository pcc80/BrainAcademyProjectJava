package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
 
public class ConnectingToDataBase {

    static final String URL = "jdbc:mysql://194.28.172.166:3306/prodavas_airport";
    static final String USER = "prodavas_airport";
    static final String PASSWORD = "testpass";

    public static Connection con;
    public static Statement stmt;
    public static ResultSet rs;
    
    public void connect() {

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = con.createStatement();
        } catch (SQLException sqlEx) {
            JOptionPane.showMessageDialog(null, "Not conection to DB! ", "CONNECTION", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    public int GetCounter (String query) throws SQLException {
        connect();
        rs = stmt.executeQuery(query);
        rs.last();
        int counter = rs.getRow();
        return counter;
    }
    
    public void logout() throws SQLException{
        rs.close();
        stmt.close();
        con.close();
    }

}