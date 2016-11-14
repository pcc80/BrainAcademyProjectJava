package DataBase;

import java.sql.SQLException;

public class SearchPassanger {
    public String[][] searchPassanger(String argument) throws SQLException{
        argument = argument + "*";
        String [][] passanger = null;
        ConnectingToDataBase connection = new ConnectingToDataBase();
        connection.connect();
        String query = "SELECT * FROM users WHERE first_name = \""+argument
                +"\" OR last_name = \""+argument
                +"\" OR passport = \""+argument+"\"";
        connection.rs = connection.stmt.executeQuery(query);
        while(connection.rs.next()){
            
        }
        
        return passanger;
    }
}
