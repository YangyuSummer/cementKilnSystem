package com.wang.controller;


import com.wang.common.Constants;
import com.wang.entity.Repository;
import com.wang.service.RepositoryService;
import com.wang.utils.PageUtil;
import com.wang.utils.Result;
import com.wang.utils.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class JqgridRepoController {

    @Resource
    private RepositoryService repositoryService;

    @RequestMapping("/admin/jqgridRepository")
    public String tojqgrid(HttpServletRequest request){
        request.setAttribute("path","jqgridRepository");
        return "jqgridRepo";
    }

    @RequestMapping("/sss")
    @ResponseBody
    public Result jqgrid(@RequestParam Map<String, Object> params){

        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        PageUtil pageUtil = new PageUtil(params);
        return ResultGenerator.genSuccessResult(repositoryService.getAdminUserPage(pageUtil));
    }

    @RequestMapping("/admin/jqgridToAdd")
    public String toADD(){
        return "jqgridAddWaste";
    }

    @RequestMapping("/admin/jqgridAddWaste")
    public String jAddWaste(Repository repository){

        repositoryService.addWaste(repository);
        return "redirect:/admin/jqgridRepository";

    }

    @RequestMapping("/admin/toEditWaste")
    public String tojqEdit(@RequestParam("wasteID") String wasteID, Model model){

        Repository repository = repositoryService.queryWasteByID(wasteID);
        model.addAttribute("jqWaste",repository);

        System.out.println(wasteID);
        return "jqgridEditWaste";
    }

    @RequestMapping("/admin/jqgridEditWaste")
    public String jqeditWas(Repository repository){

        repositoryService.updateWasteByID(repository);
        return "redirect:/admin/jqgridRepository";

    }

    @RequestMapping("/admin/jqDeleteWaste")
    public String jqDelete(@RequestParam("wasteID") String wasteID){

        repositoryService.deleteWasteByID(wasteID);
        return "redirect:/admin/jqgridRepository";

    }


}
