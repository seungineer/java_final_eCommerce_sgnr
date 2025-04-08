package dto;

public class UserModifyDTO {
    private String id;
    private String name;
    private String mobileNo;

    public String getId() {
        return id;
    }

    public void setId(String idUser) {
        this.id = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobile) {
        this.mobileNo = mobile;
    }
}
