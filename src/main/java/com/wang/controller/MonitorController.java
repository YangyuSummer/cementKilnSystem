package com.wang.controller;

import com.wang.entity.Echarts;
import com.wang.service.MonitorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MonitorController {

    @Resource
    private MonitorService monitorService;

    @RequestMapping("/admin/monitor")
    public String toMonitor(HttpServletRequest request, Model model){

        request.setAttribute("path","monitor");
        return "monitor";

    }

    @RequestMapping("/admin/supplement")
    public String toTest(HttpServletRequest request){

        request.setAttribute("path","supplement");
        return "monitorTest";
    }

    @RequestMapping("/echarts/getJson")
    @ResponseBody
    public Map<String, Object> toEcha(){
        System.out.println("进入");
        Map<String, Object> map = new HashMap<>();
        List<Echarts> list = new ArrayList<>();
        list.add(new Echarts("污泥",monitorService.aveHeatValueSludge(),monitorService.sumWeightSludge()));
        list.add(new Echarts("工业垃圾",monitorService.aveHeatValueIndustry(), monitorService.sumWeightIndustry()));
        list.add(new Echarts("废漆渣", monitorService.aveHeatValuePaint(), monitorService.sumWeightPaint()));
        list.add(new Echarts("乳化液", monitorService.aveHeatValueEmulsion(), monitorService.sumWeightEmulsion()));
        list.add(new Echarts("矿物油", monitorService.aveHeatValueOil(), monitorService.sumWeightOil()));
        list.add(new Echarts("有机溶剂", monitorService.aveHeatValueOrganic(), monitorService.sumWeightOrganic()));
        list.add(new Echarts("化学试剂", monitorService.aveHeatValueChemistry(), monitorService.sumWeightChemistry()));

        map.put("list",list);
        map.put("status","ok");
        return map;
    }

    @RequestMapping("/echarts/getJsonPie")
    @ResponseBody
    public Map<String, Object> toEchaa(){
        System.out.println("进入2");
        Map<String, Object> mapPie = new HashMap<>();
        List<Echarts> list = new ArrayList<>();
        list.add(new Echarts("污泥",monitorService.aveHeatValueSludge(),monitorService.sumWeightSludge()));
        list.add(new Echarts("工业垃圾",monitorService.aveHeatValueIndustry(), monitorService.sumWeightIndustry()));
        list.add(new Echarts("废漆渣", monitorService.aveHeatValuePaint(), monitorService.sumWeightPaint()));
        list.add(new Echarts("乳化液", monitorService.aveHeatValueEmulsion(), monitorService.sumWeightEmulsion()));
        list.add(new Echarts("矿物油", monitorService.aveHeatValueOil(), monitorService.sumWeightOil()));
        list.add(new Echarts("有机溶剂", monitorService.aveHeatValueOrganic(), monitorService.sumWeightOrganic()));
        list.add(new Echarts("化学试剂", monitorService.aveHeatValueChemistry(), monitorService.sumWeightChemistry()));

        mapPie.put("list2",list);
        mapPie.put("status2","ok");
        return mapPie;
    }






}
