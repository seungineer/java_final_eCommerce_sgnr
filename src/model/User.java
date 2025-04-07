package model;

public class User {
    private String idUser;
    private String nmUser;
    private String nmPaswd;
    private String nmEncPaswd;
    private String noMobile;
    private String nmEmail;
    private String stStatus;
    private String cdUserType;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNmUser() {
        return nmUser;
    }

    public void setNmUser(String nmUser) {
        this.nmUser = nmUser;
    }

    public String getNmPaswd() {
        return nmPaswd;
    }

    public void setNmPaswd(String nmPaswd) {
        this.nmPaswd = nmPaswd;
    }

    public String getNmEncPaswd() {
        return nmEncPaswd;
    }

    public void setNmEncPaswd(String nmEncPaswd) {
        this.nmEncPaswd = nmEncPaswd;
    }

    public String getNoMobile() {
        return noMobile;
    }

    public void setNoMobile(String noMobile) {
        this.noMobile = noMobile;
    }

    public String getNmEmail() {
        return nmEmail;
    }

    public void setNmEmail(String nmEmail) {
        this.nmEmail = nmEmail;
    }

    public String getStStatus() {
        return stStatus;
    }

    public void setStStatus(String stStatus) {
        this.stStatus = stStatus;
    }

    public String getCdUserType() {
        return cdUserType;
    }

    public void setCdUserType(String cdUserType) {
        this.cdUserType = cdUserType;
    }
}
