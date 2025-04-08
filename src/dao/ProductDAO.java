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
        String sql = "INSERT INTO TB_PRODUCT (no_product, nm_product, nm_detail_explain, qt_sale_price, qt_stock, dt_start_date, dt_end_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection()) {
            String productCode = generateProductCode(conn);  // 시퀀스로 생성
            dto.setProductCode(productCode);

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, dto.getProductCode());
                pstmt.setString(2, dto.getProductName());
                pstmt.setString(3, dto.getDetail());
                pstmt.setInt(4, dto.getSalePrice());
                pstmt.setInt(5, dto.getStock());
                pstmt.setString(6, dto.getStartDate());
                pstmt.setString(7, dto.getEndDate());

                return pstmt.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Product> getAllProducts() {
        String sql = "SELECT no_product, nm_product, nm_detail_explain, qt_sale_price, qt_stock, dt_start_date, dt_end_date FROM TB_PRODUCT"
            + " WHERE TO_DATE(dt_start_date, 'YYYYMMDD') <= SYSDATE AND TO_DATE(dt_end_date, 'YYYYMMDD') >= SYSDATE";
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
                product.setEndDate(rs.getString("dt_end_date"));
                product.setDetailExplain(rs.getString("nm_detail_explain"));

                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    public String generateProductCode(Connection conn) throws SQLException {
        // chatGPT 인용
        String sql = "SELECT SEQ_PRODUCT_NO.NEXTVAL FROM DUAL";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return "Product" + rs.getInt(1);
            }
            return null;
        }
    }

    public boolean updateProduct(ProductInsertDTO dto) {
        String sql = "UPDATE TB_PRODUCT " +
                "SET nm_product = ?, nm_detail_explain = ?, qt_sale_price = ?, qt_stock = ?, dt_start_date = ?, dt_end_date = ? " +
                "WHERE no_product = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dto.getProductName());
            pstmt.setString(2, dto.getDetail());
            pstmt.setInt(3, dto.getSalePrice());
            pstmt.setInt(4, dto.getStock());
            pstmt.setString(5, dto.getStartDate());
            pstmt.setString(6, dto.getEndDate());
            pstmt.setString(7, dto.getProductCode());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(String productCode) {
        String sql = "DELETE FROM TB_PRODUCT WHERE no_product = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, productCode);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProductQuantity(String productCode, int updateStock) {
        String sql = "UPDATE TB_PRODUCT SET qt_stock = ? WHERE no_product = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, updateStock);
            pstmt.setString(2, productCode);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
