package view;

import java.util.Scanner;

public class UserMenuView {
    public static void showUserMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n===== 사용자 메뉴 =====");
            System.out.println("1. 상품 목록 보기");
            System.out.println("2. 회원 정보 수정");
            System.out.println("3. 비밀번호 변경");
            System.out.println("4. 탈퇴 요청");
            System.out.println("0. 로그아웃");

            System.out.print("선택: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    ProductListView.render();
                    break;
                case "2":
                    // UserMenuView.renderModifyUser();
                    break;
                case "3":
                    // UserMenuView.renderChangeUserPW();
                    break;
                case "4":
                    // UserMenuView.renderSignOutUser();
                    break;
                case "0":
                    isRunning = false;
                    System.out.println("로그아웃 되었습니다.");
                    break;
            }
        }
    }

    public static void renderModifyUser() {
        System.out.println("\n[회원 정보 수정]");
        // 사용자 이름, 연락처 등 수정 입력 → controller 호출
        // UserController.updateUserInfo(dto)
        System.out.println("아직 구현되지 않았습니다.");
    }

    public static void renderChangeUserPW() {
        System.out.println("\n[비밀번호 변경]");
        // 현재 비밀번호 확인 → 새 비밀번호 2번 입력
        // UserController.changePassword(email, oldPw, newPw)
        System.out.println("아직 구현되지 않았습니다.");
    }

    public static void renderSignOutUser() {
        System.out.println("\n[탈퇴 요청]");
        // 회원 탈퇴 플래그 (st_status = ST02)로 변경 요청
        // UserController.deactivateUser()
        System.out.println("아직 구현되지 않았습니다.");
    }
}
