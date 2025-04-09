package exception;

public class PasswordMismatchException extends Exception {
    public PasswordMismatchException() {
        super("현재 비밀번호가 일치하지 않습니다.");
    }
}
