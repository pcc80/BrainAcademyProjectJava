package Main;

import GUI.LoginWindow;

public class Main {
    
    private static String versionApp = "v.1.0.0";
    private static int accessLevel;
    private static String firstName;
    private static String lastName;
    
    public static int getAccessLevel() {
        return accessLevel;
    }

    public static void setAccessLevel(int aAccessLevel) {
        accessLevel = aAccessLevel;
    }
    
    public static String getVersionApp () {
        return versionApp;
    }
    
    public static void setVersionApp (String aVersionApp) {
        versionApp = aVersionApp;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String aFirstName) {
        firstName = aFirstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String aLastName) {
        lastName = aLastName;
    }



    public static void main(String[] args) {

        LoginWindow loginWindow = new GUI.LoginWindow();
        loginWindow.loginingWindows();

    }

}