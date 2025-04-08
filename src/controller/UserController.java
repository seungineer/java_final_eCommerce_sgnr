package controller;

import dao.UserDAO;
import dto.UserModifyDTO;

public class UserController {
    private UserDAO userDAO = new UserDAO();

    public boolean updateUserInfo(UserModifyDTO dto) {
        return userDAO.updateUserInfo(dto);
    }

    public boolean changePassword(String idUser, String oldPassword, String newPassword) {
        return userDAO.updatePassword(idUser, oldPassword, newPassword);
    }
}
