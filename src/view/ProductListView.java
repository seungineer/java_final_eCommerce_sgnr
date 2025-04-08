package view;

import dao.ProductDAO;
import model.Product;

import java.util.List;

public class ProductListView {
    public static void render() {
        ProductDAO productDAO = new ProductDAO();
        List< Product> productList = productDAO.getAllProducts();

        System.out.println("\n===== 상품 목록 =====");
        if (productList.isEmpty()) {
            System.out.println("등록된 상품이 없습니다.");
            return;
        }

        for (Product product : productList) {
            System.out.println("상품명: " + product.getProductName());
            System.out.println("가격: " + product.getSalePrice());
            System.out.println("재고: " + product.getStock());
            System.out.println("상품코드: " + product.getProductCode());
            System.out.println("-------------------------");
        }
    }
}
