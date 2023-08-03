//сохранение данных в файл
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UserDataFileManager {
    public static void saveUserDataToFile(UserData user) throws IOException {
        String filename = user.getLastName() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(user.toString());
            writer.newLine();
        }
    }
}
