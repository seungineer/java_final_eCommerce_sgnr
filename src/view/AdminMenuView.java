package view;

import controller.AdminProductController;
import dao.ProductDAO;
import dto.ProductInsertDTO;
import exception.NotValidModifyQuantityCommandException;
import exception.NotValidProductInputException;
import model.Product;
import util.UserSession;

import java.util.List;
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
            System.out.println("6. 상품 판매 여부 관리");
            System.out.println("0. 로그아웃");

            System.out.print("선택: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    ProductListView.render();
                    break;
                case "2":
                    renderProductInsert();
                    break;
                case "3":
                    renderModifyProduct();
                    break;
                case "4":
                    renderDeleteProduct();
                    break;
                case "5":
                    renderUpdateProductQuantity();
                    break;
                case "6":
                    renderModifySaleStatus();
                    break;
                case "0":
                    isRunning = false;
                    UserSession.logout();
                    System.out.println("로그아웃 되었습니다.");
                    break;
            }
        }
    }

    public static void renderProductInsert() {
        Scanner scanner = new Scanner(System.in);
        AdminProductController controller = new AdminProductController();
        ProductInsertDTO dto = new ProductInsertDTO();

        System.out.println("\n=== 상품 등록 ===");

        dto.setProductName(readValidProductName(scanner));
        dto.setDetail(readProductDetail(scanner));
        dto.setSalePrice(readValidSalePrice(scanner));
        dto.setStock(readValidStock(scanner));
        dto.setStartDate(readValidStartDate(scanner));
        dto.setEndDate(readValidEndDate(scanner));
        dto.setSaleStatus(readValidSaleStatus(scanner));

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
        ProductDAO productDAO = new ProductDAO();
        List<Product> productList = productDAO.getAllProducts();

        ProductListView.displayProducts(productList, controller);

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

        System.out.print("변경할 상품 판매 중지 여부(0: O, 1: X): ");
        dto.setSaleStatus(readValidSaleStatus(scanner));

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
        ProductDAO productDAO = new ProductDAO();
        List<Product> productList = productDAO.getAllProducts();

        ProductListView.displayProducts(productList, controller);

        while (true) {
            try {
                System.out.print("재고를 변경할 상품의 상품 코드 입력: ");
                String productCode = scanner.nextLine();

                boolean isValidCode = productList.stream()
                        .anyMatch(p -> productCode.equals(p.getProductCode()));

                if (!isValidCode) {
                    throw new NotValidModifyQuantityCommandException("존재하지 않는 상품 코드입니다.");
                }

                System.out.print("업데이트 재고 수량 입력(개): ");
                int newStock = Integer.parseInt(scanner.nextLine());

                if (newStock < 0) {
                    throw new NotValidModifyQuantityCommandException("재고 수량은 0 이상이어야 합니다.");
                }

                boolean success = controller.updateProductQuantity(productCode, newStock);

                if (success) {
                    System.out.println("상품 재고가 성공적으로 변경되었습니다.");
                } else {
                    System.out.println("재고 변경에 실패했습니다.");
                }
                break;

            } catch (NotValidModifyQuantityCommandException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("수량은 숫자로 입력해주세요.");
            } catch (Exception e) {
            }
        }
    }
    private static String readValidProductName(Scanner scanner) {
        while (true) {
            try {
                System.out.print("상품명: ");
                String name = scanner.nextLine();
                if (name.isBlank()) throw new NotValidProductInputException("상품명은 필수 입력입니다.");
                return name;
            } catch (NotValidProductInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static String readProductDetail(Scanner scanner) {
        System.out.print("상품 설명: ");
        return scanner.nextLine();
    }

    private static int readValidSalePrice(Scanner scanner) {
        while (true) {
            try {
                System.out.print("판매가: ");
                int price = Integer.parseInt(scanner.nextLine());
                if (price <= 0) throw new NotValidProductInputException("판매가는 0보다 커야 합니다.");
                return price;
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요.");
            } catch (NotValidProductInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int readValidStock(Scanner scanner) {
        while (true) {
            try {
                System.out.print("재고 수량(개): ");
                int stock = Integer.parseInt(scanner.nextLine());
                if (stock < 0) throw new NotValidProductInputException("재고 수량은 음수가 될 수 없습니다.");
                return stock;
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요.");
            } catch (NotValidProductInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static String readValidStartDate(Scanner scanner) {
        while (true) {
            System.out.print("판매 시작일: ");
            String startDate = scanner.nextLine();
            if (!startDate.isBlank()) return startDate;
            System.out.println("시작일은 필수 입력입니다.");
        }
    }

    private static String readValidEndDate(Scanner scanner) {
        while (true) {
            System.out.print("판매 종료일: ");
            String endDate = scanner.nextLine();
            if (!endDate.isBlank()) return endDate;
            System.out.println("종료일은 필수 입력입니다.");
        }
    }

    private static int readValidSaleStatus(Scanner scanner) {
        while (true) {
            try {
                int status = Integer.parseInt(scanner.nextLine());
                if (status != 0 && status != 1) {
                    throw new NotValidProductInputException("0 또는 1만 입력 가능합니다.");
                }
                return status;
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요.");
            } catch (NotValidProductInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void renderModifySaleStatus() {
        Scanner scanner = new Scanner(System.in);
        AdminProductController controller = new AdminProductController();
        ProductDAO productDAO = new ProductDAO();
        List<Product> productList = productDAO.getAllProducts();

        ProductListView.displayProducts(productList, controller);

        System.out.print("판매 상태를 변경할 상품 코드 입력: ");
        String productCode = scanner.nextLine();

        Product target = productList.stream()
                .filter(p -> p.getProductCode().equals(productCode))
                .findFirst()
                .orElse(null);

        if (target == null) {
            System.out.println("해당 상품을 찾을 수 없습니다.");
            return;
        }
        System.out.print("변경할 상품 상태(0: 상품 판매 중지 처리, 1: 상품 판매 중 처리): ");
        int newStatus = Integer.parseInt(scanner.nextLine());
        String statusLabel = (newStatus == 0) ? "판매 중지" : "판매 중";

        boolean success = controller.updateProductSaleStatus(productCode, newStatus);

        if (success) {
            System.out.println("상품이 " + statusLabel + " 상태로 변경되었습니다.");
        } else {
            System.out.println("상태 변경에 실패했습니다.");
        }
    }
}
