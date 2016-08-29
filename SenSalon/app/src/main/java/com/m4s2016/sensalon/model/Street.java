package com.m4s2016.sensalon.model;

/**
 * Created by MAGUETTE on 30/07/2016.
 */
public class Street {

    private long idStreet;
    private String region;
    private String departement;
    private String streetName;

    public Street() {
    }

    public Street(long idStreet, String region, String departement, String streetName) {
        this.idStreet = idStreet;
        this.region = region;
        this.departement = departement;
        this.streetName = streetName;
    }

    public long getIdStreet() {
        return idStreet;
    }

    public void setIdStreet(long idStreet) {
        this.idStreet = idStreet;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
