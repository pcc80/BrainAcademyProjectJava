package DataBase;

import GUI.ViewPassanger;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class DeletePassanger {
    
    public String[] DeletingFirstStep (String arg) throws SQLException {

        ConnectingToDataBase connection = new ConnectingToDataBase ();
        connection.connect();
        
        connection.rs = connection.stmt.executeQuery(
                "SELECT p.id, p.first_name, p.last_name, s.sex, p.date_of_birthday, p.nationality, p.passport, f.flight_number, salun.salun_type_full "
                + "FROM passangers AS p "
                + "INNER JOIN sex AS s ON p.sex = s.id "
                + "INNER JOIN flight_number AS f ON p.flyght_number = f.id "
                + "INNER JOIN salun_class AS salun ON p.salun_class = salun.id "
                + "WHERE p.id = "+ arg);

        String [] passangerInfo = new String [7];
        int i = 0;

        while (connection.rs.next()) {
            passangerInfo[i++] = connection.rs.getString("id");
            passangerInfo[i++] = connection.rs.getString("first_name");
            passangerInfo[i++] = connection.rs.getString("last_name");
            passangerInfo[i++] = connection.rs.getString("sex");
            passangerInfo[i++] = connection.rs.getString("date_of_birthday");
            passangerInfo[i++] = connection.rs.getString("nationality");
            passangerInfo[i] = connection.rs.getString("passport");
        }
        connection.logout();
        
        i = 0;
        int confirm = JOptionPane.showConfirmDialog(
            null, 
            "ID : " + passangerInfo[i++]+"\n"
            + "First Name : " + passangerInfo[i++]+"\n"
            + "Last Name : " + passangerInfo[i++]+"\n"
            + "Sex : " + passangerInfo[i++]+"\n"
            + "Birthday : " + passangerInfo[i++]+"\n"
            + "Nationality : " + passangerInfo[i++]+"\n"
            + "Passport : " + passangerInfo[i++]+"\n",
            "DELETE", 
            JOptionPane.OK_CANCEL_OPTION
        );
        
        if (confirm == JOptionPane.CANCEL_OPTION) {}
        else if (confirm == JOptionPane.OK_OPTION) {
            DeletePassanger delete = new DeletePassanger();
            delete.DeletingSecondStep(passangerInfo[0]);
        }
        
        return passangerInfo;
    }
    
    public void DeletingSecondStep (String arg) throws SQLException {
        ConnectingToDataBase connection = new ConnectingToDataBase ();
        connection.connect();
        
        connection.rs = connection.stmt.executeQuery("DELETE FROM passangers WHERE p.id = "+ arg);

        JOptionPane.showMessageDialog(null, "Passanger id=" + arg + " deleted from database !", "Sucessfull", JOptionPane.PLAIN_MESSAGE);
        ViewPassanger vp = new ViewPassanger();
        vp.ViewPassanger();
        
    }
    
}
