import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    public static void saveData(List<String> data, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving to " + fileName);
        }
    }


    public static List<String> loadData(String fileName) {
        List<String> data = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) return data;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error loading from " + fileName);
        }
        return data;
    }
}   