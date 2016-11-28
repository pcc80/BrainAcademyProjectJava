package DataBase;

import java.sql.SQLException;

public class QueryLogin {
    
    public boolean queryLogin (String login, String password) throws SQLException {
        
        boolean info = false;
        String tempPassword = password, firstName, lastName;
        int accessLevel;
        
        ConnectingToDataBase connection = new ConnectingToDataBase();
        connection.connect();
        String query = "select password, access_level, first_name, last_name from users where login = '" + login + "'";
        connection.rs = connection.stmt.executeQuery(query);

        while (connection.rs.next()) {
            password = connection.rs.getString("password");
            accessLevel = connection.rs.getInt("access_level");
            firstName = connection.rs.getString("first_name");
            lastName = connection.rs.getString("last_name");
            if (tempPassword.equals(password)) {
                info = true;
                Main.Main.setAccessLevel(accessLevel);
                Main.Main.setFirstName(firstName);
                Main.Main.setLastName(lastName);
            }
        }

        connection.logout();
        return info;

    }

}