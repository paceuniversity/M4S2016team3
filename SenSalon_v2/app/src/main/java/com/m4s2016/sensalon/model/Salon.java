package com.m4s2016.sensalon.model;

/**
 * Created by MAGUETTE on 30/07/2016.
 */
public class Salon {

    private long idSalon;
    private String nomSalon;
    private String longitude;
    private String latitude;
    private String adresse;
    private String telephone;
    private String typeSalon;
//    private Street street;
//    private Proprietaire proprietaire;
    private long streetId;
    private long proprietaireId;

    public Salon() {
    }

    public Salon(long idSalon, String nomSalon, String longitude, String latitude, String adresse, String telephone, String typeSalon) {
        this.idSalon = idSalon;
        this.nomSalon = nomSalon;
        this.longitude = longitude;
        this.latitude = latitude;
        this.adresse = adresse;
        this.telephone = telephone;
        this.typeSalon = typeSalon;
    }

    public long getIdSalon() {
        return idSalon;
    }

    public void setIdSalon(long idSalon) {
        this.idSalon = idSalon;
    }

//    public Proprietaire getProprietaire() {
//        return proprietaire;
//    }
//
//    public void setProprietaire(Proprietaire proprietaire) {
//        this.proprietaire = proprietaire;
//    }
//
//    public Street getStreet() {
//        return street;
//    }
//
//    public void setStreet(Street street) {
//        this.street = street;
//    }

    public long getStreetId() {
        return streetId;
    }

    public void setStreetId(long streetId) {
        this.streetId = streetId;
    }

    public long getProprietaireId() {
        return proprietaireId;
    }

    public void setProprietaireId(long proprietaireId) {
        this.proprietaireId = proprietaireId;
    }

    public String getTypeSalon() {
        return typeSalon;
    }

    public void setTypeSalon(String typeSalon) {
        this.typeSalon = typeSalon;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNomSalon() {
        return nomSalon;
    }

    public void setNomSalon(String nomSalon) {
        this.nomSalon = nomSalon;
    }
}