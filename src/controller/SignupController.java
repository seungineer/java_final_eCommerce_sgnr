package controller;

import dao.UserDAO;
import dao.UserInsertDTO;

public class SignupController {
    private UserDAO userDAO = new UserDAO();

    public boolean signup(UserInsertDTO dto) {
        // FixMe: email 중복 검사
        return userDAO.insertUser(dto);
    }
}
