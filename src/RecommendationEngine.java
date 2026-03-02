import java.io.*;
import java.time.LocalDate;

public class RecommendationEngine {

    public static String generateRecommendation() throws IOException {

        BufferedReader br = DataManager.readFile("workout_history.txt");
        LocalDate today = LocalDate.now();
        LocalDate lastWorkout = null;

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            LocalDate date = LocalDate.parse(parts[0]);

            if (lastWorkout == null || date.isAfter(lastWorkout)) {
                lastWorkout = date;
            }
        }
        br.close();

        if (lastWorkout == null)
            return "Start with a light full-body workout.";

        if (lastWorkout.isBefore(today.minusDays(3)))
            return "You missed workouts. Try 20 minutes walking today.";

        return "Great consistency! Try strength training today.";
    }
}