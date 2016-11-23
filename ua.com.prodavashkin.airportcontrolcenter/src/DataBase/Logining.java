package DataBase;

import java.sql.SQLException;

public class Logining {

    public boolean userVerification (String textLogin, String textPassword) throws SQLException{

        boolean verificator = false;

        QueryLogin ql = new QueryLogin();

        if (ql.queryLogin(textLogin, textPassword) == true) {
            verificator = true;
        }

        return verificator;
    }
}