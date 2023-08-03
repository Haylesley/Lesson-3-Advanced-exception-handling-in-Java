// Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке,
// разделенные пробелом (данные вводятся одной строкой без запятых):
// Фамилия Имя Отчество, дата рождения, номер телефона, пол
//
// Форматы данных:
// фамилия, имя, отчество - строки
// дата рождения - строка формата dd.mm.yyyy
// номер телефона - целое беззнаковое число без форматирования
// пол - символ латиницей f или m.
//
// Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым,
// вернуть код ошибки. Создать метод, который обработает его и покажет пользователю сообщение,
// что он ввел меньше или больше данных, чем требуется.
//
// Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры.
// Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы.
// Можно использовать встроенные типы java или создать свои. Исключение должно быть корректно обработано,
// пользователю выведено сообщение с информацией, что именно неверно.
//
// Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии.
// В него в одну строку должны записаться полученные данные, вида
//
// Фамилия Имя Отчество, дата рождения, номер телефона, пол
//
// Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
//
// Не забудьте закрыть соединение с файлом.
//
// При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано,
// пользователь должен увидеть стектрейс ошибки.
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите данные в произвольном порядке (Фамилия Имя Отчество, дата рождения, номер телефона, пол): ");
        String userData = scanner.nextLine().trim();// trim удаляет пробелы в начале и конце строки

        String lastName = "";
        String firstName = "";
        String middleName = "";
        String dateOfBirthStr = "";
        String phoneNumber = "";
        char gender = '\0';

        String[] data = userData.split("\\s+");//регулярное выражение \\s+ будет соответствовать строке,
        // содержащей один или более пробельных символов подряд

        for (String item : data) {
            if (item.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")) {
                dateOfBirthStr = item;
            } else if (item.matches("[0-9]+")) {
                phoneNumber = item;
            } else if (item.equalsIgnoreCase("f") || item.equalsIgnoreCase("m")) {
                gender = item.charAt(0);
            } else {
                if (lastName.isEmpty()) {
                    lastName = item;
                } else if (firstName.isEmpty()) {
                    firstName = item;
                } else {
                    middleName = item;
                }
            }
        }

        try {
            UserDataParser parser = new UserDataParser();
            UserData user = parser.parseUserData(lastName, firstName, middleName, dateOfBirthStr, phoneNumber, gender);
            UserDataFileManager.saveUserDataToFile(user);
            System.out.println("Данные успешно сохранены в файл.");
        } catch (UserDataFormatException | IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}

