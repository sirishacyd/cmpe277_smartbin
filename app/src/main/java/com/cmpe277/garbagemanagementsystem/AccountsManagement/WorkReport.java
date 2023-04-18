package com.cmpe277.garbagemanagementsystem.AccountsManagement;

public class WorkReport {

    public WorkReport(){}

    public WorkReport(String binID, String address, String driverMail,
                      String loadType, String cycle,
                      String time, String todayCleaning) {
        this.binID = binID;
        this.address = address;
        this.driverMail = driverMail;
        this.loadType = loadType;
        this.cycle = cycle;
        this.time = time;
        this.todayCleaning = todayCleaning;
    }

    public String getBinID() {
        return binID;
    }

    public void setBinID(String binID) {
        this.binID = binID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDriverMail() {
        return driverMail;
    }

    public void setDriverMail(String driverMail) {
        this.driverMail = driverMail;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTodayCleaning() {
        return todayCleaning;
    }

    public void setTodayCleaning(String todayCleaning) {
        this.todayCleaning = todayCleaning;
    }

    private String binID;
    private String address;
    private String driverMail;
    private String loadType;
    private String cycle;
    private String time;
    private String date;
    private String todayCleaning;
}