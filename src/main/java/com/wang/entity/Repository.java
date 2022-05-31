package com.wang.entity;

import java.sql.Date;

public class Repository {
    private String wasteID;
    private String wasteName;
    private String resourceCompany;
    private Date repoTime;
    private String solveEquipment;
    private int weight;
    private int heatValue;
    private float s;
    private float p;
    private float ho2;
    private float c;

    public String getWasteID() {
        return wasteID;
    }

    public void setWasteID(String wasteID) {
        this.wasteID = wasteID;
    }

    public String getWasteName() {
        return wasteName;
    }

    public void setWasteName(String wasteName) {
        this.wasteName = wasteName;
    }

    public String getResourceCompany() {
        return resourceCompany;
    }

    public void setResourceCompany(String resourceCompany) {
        this.resourceCompany = resourceCompany;
    }

    public Date getRepoTime() {
        return repoTime;
    }

    public void setRepoTime(Date repoTime) {
        this.repoTime = repoTime;
    }

    public String getSolveEquipment() {
        return solveEquipment;
    }

    public void setSolveEquipment(String solveEquipment) {
        this.solveEquipment = solveEquipment;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeatValue() {
        return heatValue;
    }

    public void setHeatValue(int heatValue) {
        this.heatValue = heatValue;
    }

    public float getS() {
        return s;
    }

    public void setS(float s) {
        this.s = s;
    }

    public float getP() {
        return p;
    }

    public void setP(float p) {
        this.p = p;
    }

    public float getHo2() {
        return ho2;
    }

    public void setHo2(float ho2) {
        this.ho2 = ho2;
    }

    public float getC() {
        return c;
    }

    public void setC(float c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "wasteID='" + wasteID + '\'' +
                ", wasteName='" + wasteName + '\'' +
                ", resourceCompany='" + resourceCompany + '\'' +
                ", repoTime='" + repoTime + '\'' +
                ", solveEquipment='" + solveEquipment + '\'' +
                ", weight=" + weight +
                ", heatValue=" + heatValue +
                ", s=" + s +
                ", p=" + p +
                ", ho2=" + ho2 +
                ", c=" + c +
                '}';
    }
}
