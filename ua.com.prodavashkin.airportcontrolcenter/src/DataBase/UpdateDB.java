package DataBase;

import java.sql.SQLException;
import javax.swing.JOptionPane;

public class UpdateDB {
    
    
    public UpdateDB (String query) throws SQLException {
        ConnectingToDataBase conn = new ConnectingToDataBase();
        conn.connect();
        conn.stmt.executeUpdate(query);
        conn.logout();            
    }

    public UpdateDB() {
        JOptionPane.showMessageDialog(null, "No parametr for update DB !", "Error", JOptionPane.ERROR_MESSAGE);
    }
    
}
