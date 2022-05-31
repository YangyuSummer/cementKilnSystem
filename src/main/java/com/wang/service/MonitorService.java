package com.wang.service;

import java.util.Map;

public interface MonitorService {

    int sumWeightSludge();
    int sumWeightIndustry();
    int sumWeightPaint();
    int sumWeightEmulsion();
    int sumWeightOil();
    int sumWeightOrganic();
    int sumWeightChemistry();

    Map<String,Object> getWeightList();

    int aveHeatValueSludge();
    int aveHeatValueIndustry();
    int aveHeatValuePaint();
    int aveHeatValueEmulsion();
    int aveHeatValueOil();
    int aveHeatValueOrganic();
    int aveHeatValueChemistry();

}
