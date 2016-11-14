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
        System.out.println(query);
        
        connection.rs = connection.stmt.executeQuery(query);
        while (connection.rs.next()) {
            id = connection.rs.getInt("id");
            password = connection.rs.getString("password");
            accessLevel = connection.rs.getInt("access_level");
            firstName = connection.rs.getString("first_name");
            lastName = connection.rs.getString("last_name");
            System.out.printf("id: %d, password: %s, Acess Level: %d, Name: %s, Last Name: %s %n", id, password, accessLevel, firstName, lastName);
            if (tempPassword.equals(password)) {
                System.out.println("Password Correct !!!");
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
