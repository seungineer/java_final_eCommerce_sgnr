package view;

import controller.AdminProductController;
import dao.ProductDAO;
import model.Product;

import java.util.List;

public class ProductListView {
    public static void render(boolean isAdmin) {
        ProductDAO productDAO = new ProductDAO();
        AdminProductController controller = new AdminProductController();
        List< Product> productList = productDAO.getAllProducts();

        System.out.println("\n===== 상품 목록 =====");
        if (productList.isEmpty()) {
            System.out.println("등록된 상품이 없습니다.");
            return;
        }

        for (Product product : productList) {
            System.out.println("상품명: " + controller.diplayProductName(product));
            System.out.println("가격: " + product.getSalePrice());
            System.out.println("재고: " + product.getStock());
            if (isAdmin) System.out.println("상품코드: " + product.getProductCode());
            System.out.println("-------------------------");
        }
    }
}
