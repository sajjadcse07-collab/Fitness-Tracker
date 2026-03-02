// import java.io.*;

// public class DataManager {

//     public static void initializeUserData() throws IOException {
//         File userDir = new File(UserSession.getUserFolderPath());
//         if (!userDir.exists()) {
//             userDir.mkdirs();
//         }
//     }

//     public static void saveToFile(String fileName, String data) throws IOException {
//         FileWriter fw = new FileWriter(UserSession.getUserFolderPath() + fileName, true);
//         fw.write(data + "\n");
//         fw.close();
//     }

//     public static BufferedReader readFile(String fileName) throws IOException {
//         return new BufferedReader(
//                 new FileReader(UserSession.getUserFolderPath() + fileName));
//     }
// }

import java.io.*;
import java.util.List;

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
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(UserSession.getUserFolderPath() + fileName));
        return bufferedReader;
    }

    public static void saveToFile(List<String> data, String data2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveToFile'");
    }

    public static List<String> loadData(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadData'");
    }

    public static void saveData(List<String> data, String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveData'");
    }
}