package controller;

import dao.UserDAO;
import dto.UserModifyDTO;
import exception.PasswordMismatchException;
import exception.UpdateFailedException;

public class UserController {
    private UserDAO userDAO = new UserDAO();

    public boolean updateUserInfo(UserModifyDTO dto) {
        try {
            return userDAO.updateUserInfo(dto);
        } catch(UpdateFailedException e) {
            return false;
        }
    }

    public boolean changePassword(String idUser, String oldPassword, String newPassword) {
        try {
            return userDAO.updatePassword(idUser, oldPassword, newPassword);
        } catch (PasswordMismatchException e) {
            throw new RuntimeException(e);
        } catch (UpdateFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deactivateUser(String idUser) {
        return userDAO.updateStatusToWithdraw(idUser);
    }
}
