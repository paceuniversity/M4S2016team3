package com.m4s2016.sensalon.model;

/**
 * Created by MAGUETTE on 30/07/2016.
 */
public class Proprietaire {

    private long idProprietaire;
    private String prenom;
    private String nom;
    private String adresse;
    private String telephone;

    public Proprietaire() {
    }

    public Proprietaire(long idProprietaire, String prenom, String nom, String adresse, String telephone) {
        this.idProprietaire = idProprietaire;
        this.prenom = prenom;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    public long getIdProprietaire() {
        return idProprietaire;
    }

    public void setIdProprietaire(long idProprietaire) {
        this.idProprietaire = idProprietaire;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
