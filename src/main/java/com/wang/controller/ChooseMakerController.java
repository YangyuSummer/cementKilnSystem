package com.wang.controller;


import com.wang.common.Constants;
import com.wang.core.makeSelect;
import com.wang.entity.ElementLimit;
import com.wang.entity.Plan;
import com.wang.entity.Repository;
import com.wang.service.ElementLimitService;
import com.wang.service.PlanService;
import com.wang.service.RepositoryService;
import com.wang.utils.PageUtil;
import com.wang.utils.Result;
import com.wang.utils.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class ChooseMakerController {

    @Resource
    private RepositoryService repositoryService;
    @Resource
    private PlanService planService;
    @Resource
    private ElementLimitService elementLimitService;


    @RequestMapping("/admin/chooseMaker")
    public String toChoose(HttpServletRequest request,HttpSession session){

        session.setAttribute("zj","zj");
        request.setAttribute("path","chooseMaker");
        return "chooseMaker";

    }

    @RequestMapping("/ppp")
    @ResponseBody
    public Result jqgrid3(@RequestParam Map<String, Object> params){

        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        PageUtil pageUtil = new PageUtil(params);
        return ResultGenerator.genSuccessResult(repositoryService.getAdminUserPage(pageUtil));
    }

    @RequestMapping("/admin/chooseMakerr")
    public String chooseMakerr(@RequestParam("planID") String planID, @RequestParam("wasteIDs") String wasteIDs, @RequestParam("equipment") String equipment, @RequestParam("heatValueLow") String heatValueLoww, @RequestParam("heatValueHigh") String heatValueHighh, @RequestParam("planIntro") String planIntro, HttpSession session){

        ElementLimit s = elementLimitService.queryLimitByElement("s");
        ElementLimit p = elementLimitService.queryLimitByElement("p");
        ElementLimit ho2 = elementLimitService.queryLimitByElement("ho2");
        ElementLimit c = elementLimitService.queryLimitByElement("c");

        double sminContain = s.getMinContain();
        double pminContain = p.getMinContain();
        double ho2minContain = ho2.getMinContain();
        double cminContain = c.getMinContain();

        double smaxContain = s.getMaxContain();
        double pmaxContain = p.getMaxContain();
        double ho2maxContain = ho2.getMaxContain();
        double cmaxContain = c.getMaxContain();

        System.out.println(planID);
        System.out.println(equipment);
        System.out.println(heatValueLoww);
        System.out.println(heatValueHighh);
        System.out.println(planIntro);
        System.out.println(wasteIDs);
        String[] split = wasteIDs.split(",");

        List<String> list = new ArrayList<>(Arrays.asList(split));
        List<Repository> materialList = new ArrayList<>();
        Set<Repository>  materialRemove = new HashSet<>();
        List<Repository>  materialAuto = new ArrayList<>();
        List<Repository> planList = new ArrayList<>();

        Repository waste;
        int i =0;
        int selectAllheatvalue=0;
        for ( i = 0; i < list.size(); i++) {
            Repository selectWaste = repositoryService.queryWasteByID(list.get(i));
            materialList.add(selectWaste);
            selectAllheatvalue += selectWaste.getHeatValue();
        }
        List<Repository> material = repositoryService.queryWasteByEquipment(equipment);
        for (Repository repository : material) {
            boolean flag = true;
            for(i = 0;i<materialList.size();i++){
                if((repository.getWasteID().equals(materialList.get(i).getWasteID()))){
                    flag = false;
                }
            }
            if(flag) {
                materialRemove.add(repository);
            }
        }
        System.out.println(material);
        System.out.println(materialList);
        System.out.println(materialRemove);
        Double heatValueHigh = Double.valueOf(heatValueHighh);
        Double heatValueLow = Double.valueOf(heatValueLoww);
        if(heatValueHigh>selectAllheatvalue){
            heatValueHigh=heatValueHigh-selectAllheatvalue;
        }else{
            System.out.println("error");
        }
        if(heatValueLow>selectAllheatvalue) {
            heatValueLow = heatValueLow - selectAllheatvalue;
        }else {
            heatValueLow = 0.00;
        }
        List<Repository> materialSelect = new ArrayList<>(materialRemove);
        makeSelect mkplan =new makeSelect(planID,heatValueHigh,heatValueLow,planIntro,materialSelect,materialList,smaxContain,pmaxContain,ho2maxContain,cmaxContain);
        Map<String, Double> plan1 = new HashMap<>();
        plan1=mkplan.plan();
        String[] wasteID=new String[plan1.size()];
        Double[] mass =new  Double[plan1.size()];
        i =0;
        Set<String> setplan = plan1.keySet();
        Iterator<String> plan12 = setplan.iterator();
        while (plan12.hasNext()) {
            String key=plan12.next();
            wasteID[i]=key;
            mass[i]=plan1.get(key);
            System.out.println(wasteID[i]);
            i++;
        }
        int weightold =0;
        for (i=0;i<wasteID.length;i++) {
            waste = repositoryService.queryWasteByID(wasteID[i]);
            weightold = waste.getWeight();
            waste.setWeight((int) Math.floor(mass[wasteID.length-1-i]));
            waste.setHeatValue((int) (1.0*waste.getHeatValue()/weightold*waste.getWeight()));
//            if(waste.getHeatValue()>0) {
            planList.add(waste);
//            }
        }
        for (i=wasteID.length;i<materialList.size()+wasteID.length;i++) {
            planList.add(i,materialList.get(i-wasteID.length));
        }

        int allweight =0;
        int allheat = 0;
        for (Repository repository : planList) {
            allweight +=repository.getWeight();
            allheat += repository.getHeatValue();
            System.out.println(repository);
        }
        System.out.println(allweight);
        System.out.println(allheat);
        session.setAttribute("planID="+planID,planList);

        return "redirect:/admin/chooseMaker";
    }

    @RequestMapping("/admin/planChooseMake")
    public String ttttt(Plan plan){

        planService.addPlan(plan);
        return "redirect:/admin/manage";

    }

}
