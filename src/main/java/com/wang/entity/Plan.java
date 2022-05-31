package com.wang.entity;

public class Plan {

    private String planID;
    private String equipment;
    private int proportion;
    private int heatValueLow;
    private int heatValueHigh;
    private String planIntro;

    public Plan(String planID, String equipment, int proportion, int heatValueLow, int heatValueHigh, String planIntro) {
        this.planID = planID;
        this.equipment = equipment;
        this.proportion = proportion;
        this.heatValueLow = heatValueLow;
        this.heatValueHigh = heatValueHigh;
        this.planIntro = planIntro;
    }

    public String getPlanID() {
        return planID;
    }

    public void setPlanID(String planID) {
        this.planID = planID;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public int getProportion() {
        return proportion;
    }

    public void setProportion(int proportion) {
        this.proportion = proportion;
    }

    public int getHeatValueLow() {
        return heatValueLow;
    }

    public void setHeatValueLow(int heatValueLow) {
        this.heatValueLow = heatValueLow;
    }

    public int getHeatValueHigh() {
        return heatValueHigh;
    }

    public void setHeatValueHigh(int heatValueHigh) {
        this.heatValueHigh = heatValueHigh;
    }

    public String getPlanIntro() {
        return planIntro;
    }

    public void setPlanIntro(String planIntro) {
        this.planIntro = planIntro;
    }

    @Override
    public String toString() {
        return "Maker{" +
                "planID='" + planID + '\'' +
                ", equipment='" + equipment + '\'' +
                ", proportion=" + proportion +
                ", heatValueLow=" + heatValueLow +
                ", heatValueHigh=" + heatValueHigh +
                ", planIntro='" + planIntro + '\'' +
                '}';
    }
}
