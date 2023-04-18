package com.cmpe277.garbagemanagementsystem.AccountsManagement;

public class Users {
    private String userName;
    private String userEmail;
    private String userPassword;

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    private String cnic;


    public Users() {

    }

    //Construtor with parameters
    public Users(String userName, String userEmail, String userPassword, String cnic) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.cnic = cnic;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String emailAddress) {
        this.userEmail = emailAddress;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
