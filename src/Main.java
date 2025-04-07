import view.LoginView;

public class Main {
    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== JAVA e-Commerce 시스템 ===");
            System.out.println("1. 로그인");
            System.out.println("0. 종료");
            System.out.print("선택: ");

            String input = new java.util.Scanner(System.in).nextLine();
            switch (input) {
                case "1":
                    LoginView loginView = new LoginView();
                    loginView.render();
                    break;
                case "0":
                    System.out.println("프로그램을 종료합니다.");
                    isRunning = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}