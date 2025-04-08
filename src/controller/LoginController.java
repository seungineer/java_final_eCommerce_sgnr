package controller;

import dao.UserDAO;
import dto.UserLoginRequestDTO;
import dto.UserLoginResponseDTO;
import model.User;
import util.UserSession;

public class LoginController {
    private UserDAO userDAO = new UserDAO();

    public UserLoginResponseDTO login(UserLoginRequestDTO requestDTO) {
        String email = requestDTO.getEmail();
        String password = requestDTO.getPassword();

        User user = userDAO.findByEmailAndPassword(email, password);

        if (user == null) return null;
        UserSession.login(user);

        UserLoginResponseDTO responseDTO = new UserLoginResponseDTO();
        responseDTO.setUsername(user.getNmUser());
        responseDTO.setUserType(user.getCdUserType());
        responseDTO.setUserStatus(user.getStStatus());
        return responseDTO;
    }
}
