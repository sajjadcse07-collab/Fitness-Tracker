public class UserSession {
    private static String currentUser;

    public static void setUser(String username) {
        currentUser = username;
    }

    public static String getUser() {
        return currentUser;
    }

    public static String getUserFolderPath() {
        return "data/" + currentUser + "/";
    }
}