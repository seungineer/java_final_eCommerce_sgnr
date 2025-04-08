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
                    ProductListView.render();
                    break;
                case "2":
                    AdminMenuView.renderProductInsert();
                    break;
                case "3":
                    AdminMenuView.renderModifyProduct();
                    break;
                case "4":
                    AdminMenuView.renderDeleteProduct();
                    break;
                case "5":
                    AdminMenuView.renderUpdateProductQuantity();
                    break;
            }
        }
    }

    public static void renderProductInsert() {
        Scanner scanner = new Scanner(System.in);
        AdminProductController controller = new AdminProductController();
        ProductInsertDTO dto = new ProductInsertDTO();

        System.out.println("\n=== 상품 등록 ===");
        System.out.print("상품명: ");
        dto.setProductName(scanner.nextLine());

        System.out.print("상품 설명: ");
        dto.setDetail(scanner.nextLine());

        System.out.print("판매가: ");
        dto.setSalePrice(Integer.parseInt(scanner.nextLine()));

        System.out.print("재고 수량: ");
        dto.setStock(Integer.parseInt(scanner.nextLine()));

        System.out.print("판매 시작일: ");
        dto.setStartDate(scanner.nextLine());

        System.out.print("판매 종료일: ");
        dto.setEndDate(scanner.nextLine());

        System.out.print("판매 중지 여부(0:O, 1:X): ");
        dto.setSaleStatus(Integer.parseInt(scanner.nextLine()));

        boolean success = controller.registerProduct(dto);

        if (success) {
            System.out.println("상품이 등록되었습니다.");
        } else {
            System.out.println("상품 등록에 실패했습니다.");
        }
    }

    public static void renderModifyProduct() {
        Scanner scanner = new Scanner(System.in);
        AdminProductController controller = new AdminProductController();

        ProductListView.render();

        System.out.print("수정할 상품의 상품 코드 입력: ");
        String productCode = scanner.nextLine();

        ProductInsertDTO dto = new ProductInsertDTO();
        dto.setProductCode(productCode);

        System.out.print("변경할 상품명: ");
        dto.setProductName(scanner.nextLine());

        System.out.print("변경할 상품 설명: ");
        dto.setDetail(scanner.nextLine());

        System.out.print("변경할 판매가: ");
        dto.setSalePrice(Integer.parseInt(scanner.nextLine()));

        System.out.print("변경할 재고 수량: ");
        dto.setStock(Integer.parseInt(scanner.nextLine()));

        System.out.print("변경할 판매 시작일: ");
        dto.setStartDate(scanner.nextLine());

        System.out.print("변경할 판매 종료일: ");
        dto.setEndDate(scanner.nextLine());

        boolean success = controller.updateProduct(dto);

        if (success) {
            System.out.println("상품이 성공적으로 수정되었습니다.");
        } else {
            System.out.println("상품 수정에 실패하였습니다.");
        }
    }

    public static void renderDeleteProduct() {
        Scanner scanner = new Scanner(System.in);
        AdminProductController controller = new AdminProductController();

        ProductListView.render();

        System.out.print("삭제할 상품의 상품 코드 입력: ");
        String productCode = scanner.nextLine();

        boolean success = controller.deleteProduct(productCode);

        if (success) {
            System.out.println("상품이 성공적으로 삭제되었습니다.");
        } else {
            System.out.println("상품 삭제에 실패했습니다. 상품 코드가 존재하는지 확인하세요.");
        }
    }

    public static void renderUpdateProductQuantity() {
        Scanner scanner = new Scanner(System.in);
        AdminProductController controller = new AdminProductController();

        ProductListView.render();

        System.out.print("재고를 변경할 상품의 상품 코드 입력: ");
        String productCode = scanner.nextLine();

        System.out.print("업데이트 재고 수량 입력: ");
        int newStock = Integer.parseInt(scanner.nextLine());

        boolean success = controller.updateProductQuantity(productCode, newStock);

        if (success) {
            System.out.println("상품 재고가 성공적으로 변경되었습니다.");
        } else {
            System.out.println("재고 변경에 실패했습니다.");
        }
    }

}
