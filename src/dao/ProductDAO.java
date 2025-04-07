package dao;

import dto.ProductInsertDTO;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
