package dto;

public class UserLoginRequestDTO {
    private String email;
    private String password;

    public UserLoginRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
