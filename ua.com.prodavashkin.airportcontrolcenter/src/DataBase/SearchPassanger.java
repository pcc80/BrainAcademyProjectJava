package DataBase;

import java.sql.SQLException;

public class SearchPassanger {
    public String[][] searchPassanger(String argument) throws SQLException{
        String [][] passanger = null;
        ConnectingToDataBase connection = new ConnectingToDataBase();
        connection.connect();
        String query = "SELECT * FROM users WHERE first_name LIKE \""+argument
                +"\" OR last_name LIKE \""+argument
                +"\" OR passport LIKE \""+argument+"\"" ;
        connection.rs = connection.stmt.executeQuery(query);
        while(connection.rs.next()){
            
        }
        
        return passanger;
    }
}
