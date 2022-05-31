package com.wang.entity;

public class ElementLimit {

    private String limitName;
    private String element;
    private double minContain;
    private double maxContain;
    private String comments;

    public ElementLimit() {
    }

    public ElementLimit(String limitName, String element, double minContain, double maxContain, String comments) {
        this.limitName = limitName;
        this.element = element;
        this.minContain = minContain;
        this.maxContain = maxContain;
        this.comments = comments;
    }

    public String getLimitName() {
        return limitName;
    }

    public void setLimitName(String limitName) {
        this.limitName = limitName;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public double getMinContain() {
        return minContain;
    }

    public void setMinContain(double minContain) {
        this.minContain = minContain;
    }

    public double getMaxContain() {
        return maxContain;
    }

    public void setMaxContain(double maxContain) {
        this.maxContain = maxContain;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "ElementLimit{" +
                "limitName='" + limitName + '\'' +
                ", element='" + element + '\'' +
                ", minContain=" + minContain +
                ", maxContain=" + maxContain +
                ", comments='" + comments + '\'' +
                '}';
    }
}
