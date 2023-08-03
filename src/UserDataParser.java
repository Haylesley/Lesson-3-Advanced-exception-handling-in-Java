//разбор введенных данных и создание объекта UserData
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDataParser {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String PHONE_REGEX = "^\\d+$"; //  регулярное выражение ^\d+$ будет соответствовать строкам,
    // которые состоят из одной или более цифр и не содержат других символов

    public UserData parseUserData(String input) throws UserDataFormatException {
        String[] data = input.split("\\s+");
        if (data.length != 6) {
            throw new UserDataFormatException("Неверное количество данных. Ожидалось 6, получено " + data.length);
        }

        String lastName = data[0];
        String firstName = data[1];
        String middleName = data[2];
        String dateOfBirthStr = data[3];
        String phoneNumber = data[4];

        char gender;
        if (data[5].length() != 1 || !"fm".contains(data[5])) {
            throw new UserDataFormatException("Неверное значение для поля пол. Должно быть 'f' или 'm'.");
        }
        gender = data[5].charAt(0);

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            dateFormat.setLenient(false);
            dateFormat.parse(dateOfBirthStr);
        } catch (ParseException e) {
            throw new UserDataFormatException("Неверный формат даты рождения. Ожидается формат " + DATE_FORMAT);
        }

        if (!phoneNumber.matches(PHONE_REGEX)) {
            throw new UserDataFormatException("Неверный формат номера телефона. Должны быть только цифры без разделителей.");
        }

        return new UserData(lastName, firstName, middleName, dateOfBirthStr, phoneNumber, gender);
    }
}

