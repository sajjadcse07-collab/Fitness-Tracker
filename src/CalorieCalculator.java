import java.util.HashMap;

public class CalorieCalculator {

    private static final HashMap<String, Double> MET_VALUES = new HashMap<>();

    static {
        MET_VALUES.put("Running", 9.8);
        MET_VALUES.put("Walking", 3.8);
        MET_VALUES.put("Cycling", 7.5);
        MET_VALUES.put("Pushups", 8.0);
        MET_VALUES.put("Squats", 5.0);
    }

    public static double calculateCalories(String exercise,
                                           double weightKg,
                                           double durationMinutes) {

        double met = MET_VALUES.getOrDefault(exercise, 5.0);
        double hours = durationMinutes / 60.0;
        return met * weightKg * hours;
    }
}