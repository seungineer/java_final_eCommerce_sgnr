package util;

import model.User;

public class UserSession {
    private static User loggedInUser;

    public static void login(User user) {
        loggedInUser = user;
    }

    public static void logout() {
        loggedInUser = null;
    }

    public static User getUser() {
        return loggedInUser;
    }

    public static boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public static boolean isAdmin() {
        return isLoggedIn() && "20".equals(loggedInUser.getCdUserType());
    }

    public static String getUserId() {
        return isLoggedIn() ? loggedInUser.getIdUser() : null;
    }
}
