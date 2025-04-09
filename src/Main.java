import view.LoginView;
import view.ProductListView;
import view.SignupView;

public class Main {
    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== e-Commerce 시스템 ===");
            System.out.println("1. 비회원으로 상품 목록 보기");
            System.out.println("2. 로그인");
            System.out.println("3. 회원가입");
            System.out.println("0. 종료");
            System.out.print("선택: ");

            String input = new java.util.Scanner(System.in).nextLine();
            switch (input) {
                case "1":
                    ProductListView.render();
                    break;
                case "2":
                    LoginView loginView = new LoginView();
                    loginView.render();
                    break;
                case "3":
                    SignupView signupView = new SignupView();
                    signupView.render();
                    break;
                case "0":
                    System.out.println("프로그램을 종료합니다.");
                    isRunning = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 메뉴의 숫자만 입력하세요.");
            }
        }
    }
}