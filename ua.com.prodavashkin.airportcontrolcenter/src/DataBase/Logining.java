package DataBase;

import java.sql.SQLException;
import static jdk.nashorn.internal.objects.NativeString.trim;

public class Logining {
    public boolean userVerification (String textLogin, String textPassword) throws SQLException{
        boolean verificator = false;
        QueryLogin ql = new QueryLogin();
                
        textLogin = trim(textLogin);
        textPassword = trim(textPassword);
        
        if (ql.queryLogin(textLogin, textPassword) == true) {
            verificator = true;
        }

        return verificator;
    }
}
