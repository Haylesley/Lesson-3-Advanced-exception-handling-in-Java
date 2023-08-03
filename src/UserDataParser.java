//разбор введенных данных и создание объекта UserData
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UserDataParser {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String PHONE_REGEX = "^\\d+$";// регулярное выражение ^\d+$ будет соответствовать строкам,
    // которые состоят из одной или более цифр и не содержат других символов

    public UserData parseUserData(String lastName, String firstName, String middleName, String dateOfBirthStr,
                                  String phoneNumber, char gender) throws UserDataFormatException {
        if (lastName.isEmpty() || firstName.isEmpty() || dateOfBirthStr.isEmpty() || phoneNumber.isEmpty()) {
            throw new UserDataFormatException("Не все обязательные поля заполнены.");
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            dateFormat.setLenient(false);
            Date dateOfBirth = dateFormat.parse(dateOfBirthStr);

            if (!phoneNumber.matches(PHONE_REGEX)) {
                throw new UserDataFormatException("Неверный формат номера телефона. Должны быть только цифры без разделителей.");
            }

            return new UserData(lastName, firstName, middleName, dateOfBirthStr, phoneNumber, gender);
        } catch (ParseException e) {
            throw new UserDataFormatException("Неверный формат даты рождения. Ожидается формат " + DATE_FORMAT);
        }
    }
}


