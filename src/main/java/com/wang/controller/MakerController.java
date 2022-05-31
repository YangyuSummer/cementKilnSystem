package com.wang.controller;


import com.wang.common.Constants;
import com.wang.entity.Plan;
import com.wang.entity.Repository;
import com.wang.service.PlanService;
import com.wang.service.RepositoryService;
import com.wang.utils.PageUtil;
import com.wang.utils.Result;
import com.wang.utils.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class MakerController {

    @Resource
    private RepositoryService repositoryService;
    @Resource
    private PlanService planService;

    @RequestMapping("/admin/maker")
    public String tomake(HttpServletRequest request,HttpSession session){

        session.setAttribute("wpz","wpz");
        request.setAttribute("path","maker");
        return "maker";
    }

    @RequestMapping("/nnn")
    @ResponseBody
    public Result jqgrid2(@RequestParam Map<String, Object> params){

        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        PageUtil pageUtil = new PageUtil(params);
        return ResultGenerator.genSuccessResult(repositoryService.getAdminUserPage(pageUtil));
    }

    @RequestMapping("/admin/jqMakerr")
    public String make(@RequestParam("wasteIDs") String wasteIDs,@RequestParam("planID") String planID, HttpSession session){

        String[] split = wasteIDs.split(",");

        List<String> list = new ArrayList<>(Arrays.asList(split));

        List<Repository> repoByGroup = repositoryService.getRepoByGroup(list);

        for (Repository repository : repoByGroup) {
            System.out.println(repository);
        }

        session.setAttribute("planID="+planID,repoByGroup);

        return "redirect:/admin/maker";

    }

    @RequestMapping("/admin/manage")
    public String jqmake(HttpServletRequest request,Model model){
        request.setAttribute("path","manage");
        List<Plan> plans = planService.selectAllPlan();
        model.addAttribute("plan",plans);
        return "wasteManage";
    }

    @RequestMapping("/admin/planMake")
    public String toPlanManage(Plan plan){
        planService.addPlan(plan);
        return "redirect:/admin/manage";
    }

}
