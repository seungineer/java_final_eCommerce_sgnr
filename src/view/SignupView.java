package view;

import controller.SignupController;
import dao.UserInsertDTO;
import util.Validator;

import java.util.Scanner;

public class SignupView {
    private SignupController signupController = new SignupController();

    public void render() {
        Scanner scanner = new Scanner(System.in);
        String[] signupInfo = new String[4];
        System.out.println("\n=== 회원가입 ===");
        while (true) {
            System.out.print("이메일(ID): ");
            String email = scanner.nextLine();
            if (Validator.isValidEmail(email)) {
                signupInfo[0] = email;
                break;
            } else {
                System.out.println("이메일 형식이 올바르지 않습니다.(예: abc@aaa.com)");
            }
        }
        while (true) {
            System.out.print("비밀번호: ");
            String password = scanner.nextLine();
            if (Validator.isValidPw(password)) {
                signupInfo[1] = password;
                break;
            } else {
                System.out.println("비밀번호 조건이 충족되지 않습니다.(조건: 영문자(대/소문자), 숫자 1개 이상 포함, 5 ~ 15자리)");
            }
        }

        System.out.print("이름: ");
        String name = scanner.nextLine();
        signupInfo[2] = name;
        while (true) {
            System.out.print("휴대전화(예: 010-0000-0000): ");
            String mobile = scanner.nextLine();
            if (Validator.isValidPhoneNo(mobile)) {
                signupInfo[3] = mobile;
                break;
            } else {
                System.out.println("휴대전화는 형식이 올바르지 않습니다. (예: 010-0000-0000)");
            }
        }

        UserInsertDTO dto = new UserInsertDTO();

        dto.setId(signupInfo[0]);
        dto.setEmail(signupInfo[0]);
        dto.setPassword(signupInfo[1]);
        dto.setName(signupInfo[2]);
        dto.setMobile(signupInfo[3]);

        boolean success = signupController.signup(dto);

        if (success) {
            System.out.println("회원가입이 완료되었습니다.");
        } else {
            System.out.println("회원가입에 실패했습니다.");
        }
    }
}
