package controller;

import dao.UserDAO;
import dao.UserInsertDTO;

public class SignupController {
    private UserDAO userDAO = new UserDAO();

    public boolean signup(UserInsertDTO dto) {
        if (userDAO.existsById(dto.getId())) {
            System.out.println("이미 사용 중인 이메일(Id)입니다.");
            return false;
        }
        return userDAO.insertUser(dto);
    }
}
