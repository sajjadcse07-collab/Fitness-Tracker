import java.io.*;
import java.time.LocalDate;

public class GoalManager {

    public static void saveGoal(int weeklyTarget) throws IOException {
        DataManager.saveToFile("goals.txt", "WeeklyTarget:" + weeklyTarget);
    }

    public static boolean checkWeeklyGoal() throws IOException {
        BufferedReader br = DataManager.readFile("workout_history.txt");
        int count = 0;
        LocalDate today = LocalDate.now();

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            LocalDate date = LocalDate.parse(parts[0]);

            if (date.isAfter(today.minusDays(7))) {
                count++;
            }
        }
        br.close();

        BufferedReader goalReader = DataManager.readFile("goals.txt");
        String goalLine = goalReader.readLine();
        goalReader.close();

        int target = Integer.parseInt(goalLine.split(":")[1]);

        return count >= target;
    }
}