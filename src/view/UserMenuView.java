package view;

import controller.UserController;
import dto.UserModifyDTO;
import model.User;
import util.UserSession;

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
                     renderModifyUser();
                    break;
                case "3":
                    // renderChangeUserPW();
                    break;
                case "4":
                    // renderSignOutUser();
                    break;
                case "0":
                    isRunning = false;
                    UserSession.logout();
                    System.out.println("로그아웃 되었습니다.");
                    break;
            }
        }
    }

    public static void renderModifyUser() {
        Scanner scanner = new Scanner(System.in);
        User user = UserSession.getUser(); // 현재 로그인 사용자

        if (user == null) {
            System.out.println("로그인 정보가 없습니다.");
            return;
        }

        System.out.println("\n[회원 정보 수정]");
        System.out.println("현재 이메일(ID): " + user.getIdUser());
        System.out.println("현재 이름: " + user.getNmUser());
        System.out.println("현재 휴대전화: " + user.getNoMobile());

        System.out.print("변경할 이름 입력: ");
        String newName = scanner.nextLine();

        System.out.print("변경할 휴대전화 입력: ");
        String newMobile = scanner.nextLine();

        UserModifyDTO dto = new UserModifyDTO();
        dto.setId(user.getIdUser());
        dto.setName(newName);
        dto.setMobileNo(newMobile);

        UserController controller = new UserController();
        boolean success = controller.updateUserInfo(dto);

        if (success) {
            System.out.println("회원 정보가 수정되었습니다.");
            user.setNmUser(newName);
            user.setNoMobile(newMobile);
        } else {
            System.out.println("회원 정보 수정에 실패했습니다.");
        }
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
