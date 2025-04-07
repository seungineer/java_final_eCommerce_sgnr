package view;

import controller.LoginController;
import dto.UserLoginRequestDTO;
import dto.UserLoginResponseDTO;

import java.util.Scanner;

import static view.UserMenuView.showUserMenu;

public class LoginView {
    private LoginController loginController = new LoginController();

    public void render() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== 로그인 화면 =====");
        System.out.print("이메일 입력: ");
        String email = scanner.nextLine();

        System.out.print("비밀번호 입력: ");
        String password = scanner.nextLine();
        // FixME 이메일 형식 및 비밀번호 형식 검사 필요
        UserLoginRequestDTO requestDTO = new UserLoginRequestDTO(email, password);

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
