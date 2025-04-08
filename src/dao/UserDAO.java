package dao;

import dto.UserModifyDTO;
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

    public boolean insertUser(UserInsertDTO dto) {
        String sql = "INSERT INTO TB_USER (id_user, nm_user, nm_paswd, no_mobile, nm_email, st_status, cd_user_type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dto.getEmail());
            pstmt.setString(2, dto.getName());
            pstmt.setString(3, dto.getPassword());
            pstmt.setString(4, dto.getMobile());
            pstmt.setString(5, dto.getEmail());
            pstmt.setString(6, dto.getStatus());
            pstmt.setString(7, dto.getUserType());

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserInfo(UserModifyDTO dto) {
        String sql = "UPDATE TB_USER SET nm_user = ?, no_mobile = ? WHERE id_user = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dto.getName());
            pstmt.setString(2, dto.getMobileNo());
            pstmt.setString(3, dto.getId());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}
