package view;

import controller.SignupController;
import dao.UserInsertDTO;
import util.Validator;

import java.util.Scanner;

public class SignupView {
    public void render() {
        Scanner scanner = new Scanner(System.in);
        SignupController controller = new SignupController();
        UserInsertDTO dto = new UserInsertDTO();

        System.out.println("\n=== 회원가입 ===");

        dto.setEmail(readValidEmail(scanner));
        dto.setPassword(readValidPassword(scanner));
        dto.setName(readValidUserName(scanner));
        dto.setMobile(readValidPhoneNumber(scanner));

        boolean success = controller.signup(dto);

        if (success) {
            System.out.println("회원가입이 완료되었습니다.");
        } else {
            System.out.println("회원가입에 실패했습니다.");
        }
    }

    private String readValidEmail(Scanner scanner) {
        while (true) {
            System.out.print("이메일(ID): ");
            String email = scanner.nextLine();
            if (!Validator.isValidEmail(email)) {
                System.out.println("이메일 형식이 올바르지 않습니다.");
                continue;
            }
            return email;
        }
    }

    private String readValidPassword(Scanner scanner) {
        while (true) {
            System.out.print("비밀번호: ");
            String pw = scanner.nextLine();
            if (Validator.isValidPw(pw)) return pw;
            System.out.println("비밀번호는 대/소문자, 숫자를 포함하고 5~15자여야 합니다.");
        }
    }

    private String readValidUserName(Scanner scanner) {
        while (true) {
            System.out.print("이름: ");
            String name = scanner.nextLine();
            if (!name.isBlank()) return name;
            System.out.println("이름은 비워둘 수 없습니다.");
        }
    }

    private String readValidPhoneNumber(Scanner scanner) {
        while (true) {
            System.out.print("휴대전화 (예: 010-1234-5678): ");
            String phone = scanner.nextLine();
            if (Validator.isValidPhoneNo(phone)) return phone;
            System.out.println("전화번호 형식이 올바르지 않습니다.");
        }
    }
}
