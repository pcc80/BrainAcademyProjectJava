package DataBase;

import java.sql.SQLException;

public class QueryLogin {
    public boolean queryLogin (String login, String password) throws SQLException {
        ConnectingToDataBase connection = new ConnectingToDataBase();
        boolean info = false;
        String tempPassword = password, firstName, lastName;
        int id = 0, accessLevel = 0;
        connection.connect();
        String query = "select id, password, access_level, first_name, last_name from users where login = '" + login + "'";
        
        connection.rs = connection.stmt.executeQuery(query);
        while (connection.rs.next()) {
            id = connection.rs.getInt("id");
            password = connection.rs.getString("password");
            accessLevel = connection.rs.getInt("access_level");
            firstName = connection.rs.getString("first_name");
            lastName = connection.rs.getString("last_name");
            if (tempPassword.equals(password)) {
                info = true;
                Main.Main.setAccessLevel(accessLevel);
                Main.Main.setId(id);
                Main.Main.setFirstName(firstName);
                Main.Main.setLastName(lastName);
            }
        }
        connection.logout();
        return info;
    }
}