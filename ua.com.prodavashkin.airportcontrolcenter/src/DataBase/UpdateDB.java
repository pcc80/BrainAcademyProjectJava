package DataBase;

import java.sql.SQLException;

public class UpdateDB {
    
    
    public UpdateDB (String query) throws SQLException {
        ConnectingToDataBase conn = new ConnectingToDataBase();
        conn.connect();
        conn.stmt.executeUpdate(query);
        conn.logout();            
    }
    
}
