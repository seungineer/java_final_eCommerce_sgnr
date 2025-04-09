package util;

public class Validator {
    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean isValidPw(String password) {
        if (password == null) return false;
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{5,15}$");
    }

    public static boolean isValidPhoneNo(String phoneNo) {
        if (phoneNo == null) return  false;
        return phoneNo.matches("^010-\\d{4}-\\d{4}$");
    }
}
