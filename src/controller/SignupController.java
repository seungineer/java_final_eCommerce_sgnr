package controller;

import dao.UserDAO;
import dao.UserInsertDTO;
import exception.InsertFailedException;

public class SignupController {
    private UserDAO userDAO = new UserDAO();

    public boolean signup(UserInsertDTO dto) {
        if (userDAO.existsById(dto.getId())) {
            System.out.println("이미 사용 중인 이메일(Id)입니다.");
            return false;
        }
        try {
            return userDAO.insertUser(dto);
        } catch (InsertFailedException e) {
            return false;
        }
    }
}
