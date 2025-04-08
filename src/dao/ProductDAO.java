package dao;

import dto.ProductInsertDTO;
import model.Product;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public boolean insertProduct(ProductInsertDTO dto) {
        String sql = "INSERT INTO TB_PRODUCT (no_product, nm_product, nm_detail_explain, qt_sale_price, qt_stock, dt_start_date) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dto.getProductCode());
            pstmt.setString(2, dto.getProductName());
            pstmt.setString(3, dto.getDetail());
            pstmt.setInt(4, dto.getSalePrice());
            pstmt.setInt(5, dto.getStock());
            pstmt.setString(6, dto.getStartDate());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Product> getAllProducts() {
        String sql = "SELECT no_product, nm_product, qt_sale_price, qt_stock, dt_start_date FROM TB_PRODUCT";
        List<Product> productList = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                product.setProductCode(rs.getString("no_product"));
                product.setProductName(rs.getString("nm_product"));
                product.setSalePrice(rs.getInt("qt_sale_price"));
                product.setStock(rs.getInt("qt_stock"));
                product.setStartDate(rs.getString("dt_start_date"));

                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }
}
