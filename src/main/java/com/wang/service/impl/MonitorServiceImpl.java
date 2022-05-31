package com.wang.service.impl;

import com.wang.dao.MonitorMapper;
import com.wang.service.MonitorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class MonitorServiceImpl implements MonitorService {

    @Resource
    private MonitorMapper monitorMapper;

    @Override
    public int sumWeightSludge() {
        return monitorMapper.sumWeightSludge();
    }

    @Override
    public int sumWeightIndustry() {
        return monitorMapper.sumWeightIndustry();
    }

    @Override
    public int sumWeightPaint() {
        return monitorMapper.sumWeightPaint();
    }

    @Override
    public int sumWeightEmulsion() {
        return monitorMapper.sumWeightEmulsion();
    }

    @Override
    public int sumWeightOil() {
        return monitorMapper.sumWeightOil();
    }

    @Override
    public int sumWeightOrganic() {
        return monitorMapper.sumWeightOrganic();
    }

    @Override
    public int sumWeightChemistry() {
        return monitorMapper.sumWeightChemistry();
    }

    @Override
    public Map<String, Object> getWeightList() {
        Map<String, Object> xmap = new HashMap<>();

        int[] ydata = new int[7];
        String[] xdata = new String[7];
        ydata[1]=this.sumWeightSludge();
        ydata[2]=this.sumWeightIndustry();
        ydata[3]=this.sumWeightPaint();
        ydata[4]=this.sumWeightEmulsion();
        ydata[5]=this.sumWeightOil();
        ydata[6]=this.sumWeightOrganic();
        ydata[7]=this.sumWeightChemistry();
        xdata[1]="污泥";
        xdata[2]="工业垃圾";
        xdata[3]="废漆渣";
        xdata[4]="乳化液";
        xdata[5]="矿物油";
        xdata[6]="有机溶剂";
        xdata[7]="化学试剂";
        xmap.put("xdata",xdata);
        xmap.put("ydata",ydata);
        return xmap;
    }

    @Override
    public int aveHeatValueSludge() {
        return monitorMapper.aveHeatValueSludge();
    }

    @Override
    public int aveHeatValueIndustry() {
        return monitorMapper.aveHeatValueIndustry();
    }

    @Override
    public int aveHeatValuePaint() {
        return monitorMapper.aveHeatValuePaint();
    }

    @Override
    public int aveHeatValueEmulsion() {
        return monitorMapper.aveHeatValueEmulsion();
    }

    @Override
    public int aveHeatValueOil() {
        return monitorMapper.aveHeatValueOil();
    }

    @Override
    public int aveHeatValueOrganic() {
        return monitorMapper.aveHeatValueOrganic();
    }

    @Override
    public int aveHeatValueChemistry() {
        return monitorMapper.aveHeatValueChemistry();
    }
}
