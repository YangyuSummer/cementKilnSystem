package com.wang.controller;


import com.wang.core.planexcel;
import com.wang.entity.Plan;
import com.wang.entity.Repository;
import com.wang.service.PlanService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class ManageController {

    @Resource
    private PlanService planService;

    @RequestMapping("/admin/planContent")
    public String planContent(String planID, HttpSession session, Model model){
        System.out.println(planID);

        List<Repository> planList = (List<Repository>) session.getAttribute("planID="+planID);

        model.addAttribute("planList",planList);

        return "planContent";

    }

    @RequestMapping("/admin/deletePlan")
    public String deletePlan(String planID,HttpSession session){
        System.out.println(planID);
        planService.deletePlanByID(planID);
        session.removeAttribute("planID="+planID);
        return "redirect:/admin/manage";
    }
    @RequestMapping("/admin/planExport")
    public void exportExcel(String planID, HttpSession session, HttpServletResponse response) throws Exception {
        System.out.println(planID);
        List<Repository> planList = (List<Repository>) session.getAttribute("planID="+planID);
        System.out.println(planList);
        planexcel planexcel = new planexcel(planList);
        Workbook wb =planexcel.output();
        System.out.println("achive");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode("方案"+planID+".xls", "utf-8"));

        response.flushBuffer();
        wb.write(response.getOutputStream());

    }

}
