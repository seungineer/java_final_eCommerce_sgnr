package view;

import controller.LoginController;
import dto.UserLoginRequestDTO;
import dto.UserLoginResponseDTO;
import util.Validator;

import java.util.Scanner;

public class LoginView {
    private LoginController loginController = new LoginController();

    public void render() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== 로그인 화면 =====");
        String[] loginInfo = new String[2];
        while (true) {
            System.out.print("이메일 입력: ");
            String email = scanner.nextLine();
            if (Validator.isValidEmail(email)) {
                loginInfo[0] = email;
                break;
            } else {
                System.out.println("이메일 형식이 올바르지 않습니다.(예: abc@aaa.com)");
            }
        }

        while (true) {
            System.out.print("비밀번호 입력: ");
            String password = scanner.nextLine();
            if (Validator.isValidPw(password)) {
                loginInfo[1] = password;
                break;
            } else {
                System.out.println("비밀번호 조건이 충족되지 않습니다.(조건: 영문자(대/소문자), 숫자 1개 이상 포함, 5 ~ 15자리)");
            }
        }

        UserLoginRequestDTO requestDTO = new UserLoginRequestDTO(loginInfo[0], loginInfo[1]);

        UserLoginResponseDTO responseDTO = loginController.login(requestDTO);

        if (responseDTO != null) {
            System.out.println("로그인 성공 " + responseDTO.getUsername() + "님 환영합니다.");

            if (responseDTO.getUserType().equals("20")) {
                System.out.println("관리자 권한으로 로그인하셨습니다.");
                AdminMenuView.showAdminMenu();
            } else {
                UserMenuView.showUserMenu();
            }
        } else {
            System.out.println("로그인 실패: 이메일 또는 비밀번호를 다시 확인하세요.");
        }
    }
}
