package com.m4s2016.sensalon.model;

/**
 * Created by MAGUETTE on 30/07/2016.
 */
public class Modele {

    private long idModele;
    private String modelName;
    private String modelDuration;
    private String modelPrice;
//    private Salon salon;
    private long salonId;

    public Modele() {
    }

    public Modele(long idModele, String modelName, String modelDuration, String modelPrice) {
        this.idModele = idModele;
        this.modelName = modelName;
        this.modelDuration = modelDuration;
        this.modelPrice = modelPrice;
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
    //    public Salon getSalon() {
//        return salon;
//    }
//
//    public void setSalon(Salon salon) {
//        this.salon = salon;
//    }
}
