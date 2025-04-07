package dao;

import model.User;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public User findByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM TB_USER WHERE id_user = ? AND nm_paswd = ?";
        User user = null;

        try (
                Connection conn = ConnectionManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setIdUser(rs.getString("id_user"));
                    user.setNmUser(rs.getString("nm_user"));
                    user.setNmPaswd(rs.getString("nm_paswd"));
                    user.setCdUserType(rs.getString("cd_user_type"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
