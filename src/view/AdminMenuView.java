package view;

import controller.AdminProductController;
import dto.ProductInsertDTO;

import java.util.Scanner;

public class AdminMenuView {
    public static void showAdminMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n===== 관리자 메뉴 =====");
            System.out.println("1. 상품 목록 보기");
            System.out.println("2. 상품 등록");
            System.out.println("3. 상품 수정");
            System.out.println("4. 상품 삭제");
            System.out.println("5. 상품 재고 관리");
            System.out.println("0. 로그아웃");

            System.out.print("선택: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
//                    renderProductList();
                    break;
                case "2":
                    AdminMenuView.renderProductInsertMenu();
                    break;
            }
        }
    }

    public static void renderProductInsertMenu() {
        Scanner scanner = new Scanner(System.in);
        AdminProductController controller = new AdminProductController();
        ProductInsertDTO dto = new ProductInsertDTO();

        System.out.println("\n=== 상품 등록 ===");
        System.out.print("상품 코드: ");
        dto.setProductCode(scanner.nextLine());

        System.out.print("상품명: ");
        dto.setProductName(scanner.nextLine());

        System.out.print("상품 설명: ");
        dto.setDetail(scanner.nextLine());

        System.out.print("판매가: ");
        dto.setSalePrice(Integer.parseInt(scanner.nextLine()));

        System.out.print("재고 수량: ");
        dto.setStock(Integer.parseInt(scanner.nextLine()));

        System.out.print("판매 시작일 (예: 20250407): ");
        dto.setStartDate(scanner.nextLine());

        boolean success = controller.registerProduct(dto);

        if (success) {
            System.out.println("상품이 등록되었습니다.");
        } else {
            System.out.println("상품 등록에 실패했습니다.");
        }
    }
}
