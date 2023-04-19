package com.cmpe277.garbagemanagementsystem.AccountsManagement;

public class Drivers {
    private String userName;
    private String emailAddress;
    private String userPassword;

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    private String cnic;


    public Drivers(){

    }

    //Constructor with parameters
    public Drivers(String userName,String emailAddress,String userPassword,String cnic){
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.userPassword = userPassword;
        this.cnic = cnic;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }



}
