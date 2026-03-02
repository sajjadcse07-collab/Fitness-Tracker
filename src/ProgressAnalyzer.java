import java.io.*;
import java.time.LocalDate;

public class ProgressAnalyzer {

    public static String analyzeLast7Days() throws IOException {
        BufferedReader br = DataManager.readFile("workout_history.txt");

        int recent = 0;
        int previous = 0;
        LocalDate today = LocalDate.now();

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            LocalDate date = LocalDate.parse(parts[0]);

            if (date.isAfter(today.minusDays(7))) {
                recent++;
            } else if (date.isAfter(today.minusDays(14))) {
                previous++;
            }
        }
        br.close();

        if (recent > previous)
            return "Improving 📈";
        else if (recent == previous)
            return "Stable ⚖";
        else
            return "Declining 📉";
    }
}