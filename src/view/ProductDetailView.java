package view;

import model.Product;
import util.FormatPrice;
import util.UserSession;

public class ProductDetailView {
    static void displayProductDetail(Product product) {
        System.out.println("\n=========== 상품 상세 정보 ===========");
        System.out.println("상품명       : " + product.getProductName());
        System.out.println("가격         : " + FormatPrice.koreaWon(product.getSalePrice()));
        System.out.println("재고         : " + product.getStock()+"개");
        System.out.println("상세 설명    : " + product.getDetailExplain());
        System.out.println("판매 종료일  : " + product.getEndDate());

        if (UserSession.isAdmin()) {
            System.out.println("상품 코드    : " + product.getProductCode());
        }

        System.out.println("======================================");
    }

}
