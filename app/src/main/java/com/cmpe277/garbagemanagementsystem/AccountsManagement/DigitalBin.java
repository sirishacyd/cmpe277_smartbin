package com.cmpe277.garbagemanagementsystem.AccountsManagement;

public class DigitalBin {

    public DigitalBin(String binAddress, String cityName, String binID,
                      String driverEmail,String bestRoute,String loadType,String cycle) {
        this.binAddress = binAddress;
        this.cityName = cityName;
        this.binID = binID;
        this.driverEmail = driverEmail;
        this.bestRoute = bestRoute;
        this.loadType = loadType;
        this.cycle = cycle;
    }

    private String binAddress;
    private String cityName;
    private String binID;
    private String driverEmail;
    private String bestRoute;
    private String loadType;
    private String cycle;

    public String getBinAddress() {
        return binAddress;
    }

    public void setBinAddress(String binAddress) {
        this.binAddress = binAddress;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getBinID() {
        return binID;
    }

    public void setBinID(String binID) {
        this.binID = binID;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
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

    public String getBestRoute() {
        return bestRoute;
    }

    public void setBestRoute(String bestRoute) {
        this.bestRoute = bestRoute;
    }

    public DigitalBin(){}


}
