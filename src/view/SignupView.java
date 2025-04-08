package view;

import controller.SignupController;
import dao.UserInsertDTO;

import java.util.Scanner;

public class SignupView {
    private SignupController signupController = new SignupController();

    public void render() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== 회원가입 ===");
        System.out.print("이메일(ID): ");
        String email = scanner.nextLine();

        System.out.print("비밀번호: ");
        String password = scanner.nextLine();

        System.out.print("이름: ");
        String name = scanner.nextLine();

        System.out.print("휴대전화: ");
        String mobile = scanner.nextLine();

        UserInsertDTO dto = new UserInsertDTO();

        dto.setId(email);
        dto.setEmail(email);
        dto.setPassword(password);
        dto.setName(name);
        dto.setMobile(mobile);

        boolean success = signupController.signup(dto);

        if (success) {
            System.out.println("회원가입이 완료되었습니다.");
        } else {
            System.out.println("회원가입에 실패했습니다.");
        }
    }
}
