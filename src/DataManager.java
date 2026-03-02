import java.io.*;

public class DataManager {

    public static void initializeUserData() throws IOException {
        File userDir = new File(UserSession.getUserFolderPath());
        if (!userDir.exists()) {
            userDir.mkdirs();
        }
    }

    public static void saveToFile(String fileName, String data) throws IOException {
        FileWriter fw = new FileWriter(UserSession.getUserFolderPath() + fileName, true);
        fw.write(data + "\n");
        fw.close();
    }

    public static BufferedReader readFile(String fileName) throws IOException {
        return new BufferedReader(
                new FileReader(UserSession.getUserFolderPath() + fileName));
    }
}