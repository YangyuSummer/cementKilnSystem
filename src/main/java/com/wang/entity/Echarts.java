package com.wang.entity;

public class Echarts {
    private String month;
    private int evapCapacity;
    private int precipitation;

    public Echarts(String month, int evapCapacity, int precipitation) {
        this.month = month;
        this.evapCapacity = evapCapacity;
        this.precipitation = precipitation;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getEvapCapacity() {
        return evapCapacity;
    }

    public void setEvapCapacity(int evapCapacity) {
        this.evapCapacity = evapCapacity;
    }

    public int getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(int precipitation) {
        this.precipitation = precipitation;
    }

    @Override
    public String toString() {
        return "Echarts{" +
                "month='" + month + '\'' +
                ", evapCapacity=" + evapCapacity +
                ", precipitation=" + precipitation +
                '}';
    }
}
