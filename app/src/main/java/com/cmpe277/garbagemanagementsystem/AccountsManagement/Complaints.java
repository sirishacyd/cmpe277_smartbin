package com.cmpe277.garbagemanagementsystem.AccountsManagement;

public class Complaints {
    private String binID;
    private String compAddress;
    private String compCity;
    private String title;
    private String description;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    public Complaints(String binID, String title, String description,String status) {
        this.binID = binID;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public String getBinID() {
        return binID;
    }

    public void setBinID(String binID) {
        this.binID = binID;
    }

    public String getCompAddress() {
        return compAddress;
    }

    public void setCompAddress(String compAddress) {
        this.compAddress = compAddress;
    }

    public String getCompCity() {
        return compCity;
    }

    public void setCompCity(String compCity) {
        this.compCity = compCity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Complaints(){
    }
}
