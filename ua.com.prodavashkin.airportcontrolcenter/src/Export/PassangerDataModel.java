package Export;

public class PassangerDataModel {
 
    private String firstName;
    private String lastName;
    private String sex;
    private String birthday;
    private String nationality;
    private String passport;
    private String flightNumber;
    private String salunClass;
    
 
    public PassangerDataModel() {
    }
 
    public PassangerDataModel(String name, String firstName, String lastName, String sex, String birthday, String nationality, String passport, String flightNumber, String salunClass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birthday = birthday;
        this.nationality = nationality;
        this.passport = passport;
        this.flightNumber = flightNumber;
        this.salunClass = salunClass;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getSalunClass() {
        return salunClass;
    }

    public void setSalunClass(String salunClass) {
        this.salunClass = salunClass;
    }
 
}