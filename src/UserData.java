//  хранение данных о пользователе
public class UserData {
    private String lastName;
    private String firstName;
    private String middleName;
    private String dateOfBirth;
    private String phoneNumber;
    private char gender;

    public UserData(String lastName, String firstName, String middleName, String dateOfBirth, String phoneNumber, char gender) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + middleName + ", " + dateOfBirth + ", " + phoneNumber + ", " + gender;
    }
}
