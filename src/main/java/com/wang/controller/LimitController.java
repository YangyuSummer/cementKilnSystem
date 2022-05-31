package com.wang.controller;

import com.wang.entity.ElementLimit;
import com.wang.service.ElementLimitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LimitController {

    @Resource
    private ElementLimitService elementLimitService;

    @RequestMapping("/admin/limit")
    public String LIMI(HttpServletRequest request, Model model){

        request.setAttribute("path","limit");
        model.addAttribute("limit",elementLimitService.selectAllLimit());
        return "elementLimit";

    }

    @RequestMapping("/admin/toLimitUpdate")
    public String toLimitUpdate(@RequestParam("element") String element,Model model){

        ElementLimit elementLimit = elementLimitService.queryLimitByElement(element);
        model.addAttribute("elementLimit",elementLimit);
        return "updateLimit";

    }



}
