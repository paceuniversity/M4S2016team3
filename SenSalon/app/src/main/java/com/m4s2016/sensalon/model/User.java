package com.m4s2016.sensalon.model;

/**
 * Created by MAGUETTE on 30/07/2016.
 */
public class User {

    int idUser;
    String prenom;
    String nom;
    String username;
    String password;

    public User() {
    }

    public User(int idUser, String prenom, String nom, String username, String password) {
        this.idUser = idUser;
        this.prenom = prenom;
        this.nom = nom;
        this.username = username;
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
