package com.wang.dao;

public interface MonitorMapper {

    int sumWeightSludge();
    int sumWeightIndustry();
    int sumWeightPaint();
    int sumWeightEmulsion();
    int sumWeightOil();
    int sumWeightOrganic();
    int sumWeightChemistry();

    int aveHeatValueSludge();
    int aveHeatValueIndustry();
    int aveHeatValuePaint();
    int aveHeatValueEmulsion();
    int aveHeatValueOil();
    int aveHeatValueOrganic();
    int aveHeatValueChemistry();

}
