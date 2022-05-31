package com.wang.controller;

import com.wang.core.make;
import com.wang.entity.ElementLimit;
import com.wang.entity.Plan;
import com.wang.entity.Repository;
import com.wang.service.ElementLimitService;
import com.wang.service.PlanService;
import com.wang.service.RepositoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
public class AutoMakerController {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private PlanService planService;

    @Resource
    private ElementLimitService elementLimitService;

    @RequestMapping("/admin/autoMaker")
    public String toAutoMaker(HttpServletRequest request){

        request.setAttribute("path","autoMaker");
        return "autoMaker";

    }

    @RequestMapping("/admin/autoPlanMake")
    public String autoMaker(@RequestParam("planID") String planID, @RequestParam("equipment") String equipment,@RequestParam("proportion") int proportion, @RequestParam("heatValueLow") int heatValueLow, @RequestParam("heatValueHigh") int heatValueHigh, @RequestParam("planIntro") String planIntro, HttpSession session, Model model){

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

        List<Repository> material = repositoryService.queryWasteByEquipment(equipment);
        make mkplan =new make(planID,equipment,Double.valueOf(heatValueHigh),Double.valueOf(heatValueLow),planIntro,material,smaxContain,pmaxContain,ho2maxContain,cmaxContain);
        Map<String, Double> plan1 = new HashMap<>();
        plan1=mkplan.plan();

        String[] wasteID=new String[plan1.size()];
        Double[] mass =new  Double[plan1.size()];
        int i =0;
        List<Repository> planList = new ArrayList<>();
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

            Repository waste = repositoryService.queryWasteByID(wasteID[i]);
            System.out.println(waste);
            weightold=waste.getWeight();
            waste.setWeight((int) Math.floor(mass[wasteID.length-1-i]));

            double setHeatValue = 1.0*(waste.getHeatValue())/(weightold)*waste.getWeight();

            waste.setHeatValue( (int) Math.floor(setHeatValue));
//            System.out.println(waste);
            planList.add(waste);
            weightold =0;
        }

        session.setAttribute("planID="+planID,planList);
        Plan plan = new Plan(planID, equipment, proportion, heatValueLow, heatValueHigh, planIntro);
        planService.addPlan(plan);

        return "redirect:/admin/manage";

    }



}
