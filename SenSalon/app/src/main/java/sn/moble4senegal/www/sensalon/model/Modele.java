package sn.moble4senegal.www.sensalon.model;

/**
 * Created by lionn on 30/07/2016.
 */
public class Modele {

    private String modelName;
    private String modelDuration;
    private String modelPrice;
    private int thumbnail;

    public Modele() {

    }

    public Modele(String modelName, String modelDuration, String modelPrice, int thumbnail) {
        this.modelName = modelName;
        this.modelDuration = modelDuration;
        this.modelPrice = modelPrice;
        this.thumbnail = thumbnail;
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

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
