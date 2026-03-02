// 

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    // Ensure user folder exists
    public static void initializeUserData() {
        File userDir = new File(UserSession.getUserFolderPath());
        if (!userDir.exists()) {
            userDir.mkdirs();
        }
    }

    // Save list to file (overwrite)
    public static void saveToFile(List<String> data, String fileName) {
        initializeUserData(); 

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(UserSession.getUserFolderPath() + fileName))) {
            for (String line : data) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load all lines from file
    public static List<String> loadData(String fileName) {
        initializeUserData();

        List<String> data = new ArrayList<>();
        File file = new File(UserSession.getUserFolderPath() + fileName);

        if (!file.exists()) return data;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}