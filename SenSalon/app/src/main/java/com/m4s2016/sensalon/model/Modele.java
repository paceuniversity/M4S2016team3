package com.m4s2016.sensalon.model;

/**
 * Created by MAGUETTE on 30/07/2016.
 */
public class Modele {

    private long idModele;
    private String modelName;
    private String modelDuration;
    private String modelPrice;
    private int image;
    private long salonId;

    public Modele() {
    }

    public Modele(long idModele, String modelName, String modelDuration, String modelPrice, int image) {
        this.idModele = idModele;
        this.modelName = modelName;
        this.modelDuration = modelDuration;
        this.modelPrice = modelPrice;
        this.image = image;
    }

    public long getIdModele() {
        return idModele;
    }

    public void setIdModele(long idModele) {
        this.idModele = idModele;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelDuration() {
        return modelDuration;
    }

    public void setModelDuration(String modelDuration) {
        this.modelDuration = modelDuration;
    }

    public String getModelPrice() {
        return modelPrice;
    }

    public void setModelPrice(String modelPrice) {
        this.modelPrice = modelPrice;
    }

    public long getSalonId() {
        return salonId;
    }

    public void setSalonId(long salonId) {
        this.salonId = salonId;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
