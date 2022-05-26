package com.example.lab4.bd.classBD;

public class Site {

    int id;
    String name;
    int countryID;
    int companyID;
    private double consumption;

    public Site(int id, String siteName, int countryID, int companyID) {
        this.id = id;
        this.name = siteName;
        this.countryID = countryID;
        this.companyID = companyID;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = this.consumption + consumption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }
}
