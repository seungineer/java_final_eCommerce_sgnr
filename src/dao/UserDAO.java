package dao;

import dto.UserModifyDTO;
import exception.DataAccessException;
import exception.InsertFailedException;
import exception.PasswordMismatchException;
import exception.UpdateFailedException;
import model.User;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public User findByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM TB_USER WHERE id_user = ? AND nm_paswd = ? AND st_status LIKE 'ST01'";
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
            System.out.println("DB 연결 중 오류 발생: " + e.getMessage());
        }
        return user;
    }

    public boolean insertUser(UserInsertDTO dto) throws InsertFailedException {
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
            if (result == 0) {
                throw new InsertFailedException("회원가입에 실패했습니다.");
            }
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserInfo(UserModifyDTO dto) throws UpdateFailedException {
        String sql = "UPDATE TB_USER SET nm_user = ?, no_mobile = ? WHERE id_user = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dto.getName());
            pstmt.setString(2, dto.getMobileNo());
            pstmt.setString(3, dto.getId());

            int result = pstmt.executeUpdate();
            if (result == 0) throw new UpdateFailedException("회원 정보 수정 실패");

            return result > 0;
        } catch (SQLException e) {
            throw new UpdateFailedException("DB 오류: " + e.getMessage());
        }
    }

    public boolean updatePassword(String idUser, String oldPassword, String newPassword)
            throws PasswordMismatchException, UpdateFailedException{
        //chatGPT 인용
        String checkSql = "SELECT COUNT(*) FROM TB_USER WHERE id_user = ? AND nm_paswd = ?";
        String updateSql = "UPDATE TB_USER SET nm_paswd = ? WHERE id_user = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setString(1, idUser);
            checkStmt.setString(2, oldPassword);

            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 1) {
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setString(1, newPassword);
                    updateStmt.setString(2, idUser);
                    int result = updateStmt.executeUpdate();
                    if (result == 0) throw new UpdateFailedException("비밀번호 변경 실패");
                    return result > 0;
                }
            } else {
                throw new PasswordMismatchException();
            }

        } catch (SQLException e) {
            throw new UpdateFailedException("DB 오류: " + e.getMessage());
        }
    }

    public boolean updateStatusToWithdraw(String idUser) {
        String sql = "UPDATE TB_USER SET st_status = 'ST02' WHERE id_user = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, idUser);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existsById(String idUser) {
        String sql = "SELECT COUNT(*) FROM TB_USER WHERE id_user = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, idUser);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.out.println("중복 ID 조회 중 오류 발생 "+e.getMessage());
        }
        return false;
    }
}
