package view;

import controller.AdminProductController;
import dao.ProductDAO;
import model.Product;
import util.FormatPrice;
import util.UserSession;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static view.ProductDetailView.displayProductDetail;

public class ProductListView {
    public static void render() {
        ProductDAO productDAO = new ProductDAO();
        AdminProductController controller = new AdminProductController();
        List<Product> productList = productDAO.getAllProducts();

        if (productList.isEmpty()) {
            System.out.println("등록된 상품이 없습니다.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("\n============= 상품 목록 =============");
            displayProducts(productList, controller);
            String input = getMenuSelection(scanner);

            switch (input) {
                case "1":
                    productList.sort(Comparator.comparingInt(Product::getSalePrice));
                    break;
                case "2":
                    productList.sort(Comparator.comparingInt(Product::getSalePrice).reversed());
                    break;
                case "3":
                    System.out.print("검색할 상품명을 입력하세요: ");
                    String keyword = scanner.nextLine();
                    List<Product> filtered = productList.stream()
                            .filter(p -> p.getProductName().contains(keyword))
                            .toList();
                    if (keyword == "" || filtered.isEmpty()) {
                        System.out.println("검색 결과가 없습니다.");
                    } else {
                        System.out.println("\"" + keyword + "\"" + "검색 결과: 총 "+filtered.size()+"건" );
                        displayProducts(filtered, controller);
                        System.out.println("Enter 키를 눌러 상품 목록으로 돌아가기");
                        scanner.nextLine();
                    }
                    break;
                case "4":
                    if (UserSession.isAdmin()) {
                        System.out.print("상세 정보를 볼 상품의 [상품코드]를 입력하세요: ");
                    } else {
                        System.out.print("상세 정보를 볼 상품의 [상품명]을 입력하세요: ");
                    }

                    String detailInput = scanner.nextLine();
                    Product found = findProductByInput(productList, detailInput);

                    if (found != null) {
                        displayProductDetail(found);
                        System.out.println("상품 목록으로 돌아가기(Enter 키 입력)");
                        scanner.nextLine();
                    } else {
                        System.out.println("해당 상품을 찾을 수 없습니다.");
                    }
                    break;

                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    public static void displayProducts(List<Product> products, AdminProductController controller) {
        for (Product product : products) {
            System.out.println("상품명: " + controller.diplayProductName(product));
            System.out.println("가격: " + FormatPrice.koreaWon(product.getSalePrice()));
            System.out.println("재고: " + product.getStock()+"개");
            if (UserSession.isAdmin()) System.out.println("상품코드: " + product.getProductCode());
            System.out.println("-------------------------");
        }
    }

    private static Product findProductByInput(List<Product> products, String input) {
        if (UserSession.isAdmin()) {
            return products.stream()
                    .filter(p -> input.equals(p.getProductCode()))
                    .findFirst()
                    .orElse(null);
        } else {
            return products.stream()
                    .filter(p -> p.getProductName().equalsIgnoreCase(input))
                    .findFirst()
                    .orElse(null);
        }
    }

    private static String getMenuSelection(Scanner scanner) {
        System.out.println("\n[Menu]");
        System.out.println("상품 정렬");
        System.out.println("1: 가격 오름차순");
        System.out.println("2: 가격 내림차순");
        System.out.println("상품 검색");
        System.out.println("3: 상품 이름으로 검색");
        System.out.println("상품 상세");
        System.out.println("4: 상품 상세 조회");
        System.out.println("0: 뒤로 가기");
        System.out.print("선택: ");
        return scanner.nextLine();
    }
}
