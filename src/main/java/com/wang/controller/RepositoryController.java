package com.wang.controller;

import com.wang.entity.Repository;
import com.wang.service.RepositoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class RepositoryController {

    @Resource
    private RepositoryService repositoryService;

    @RequestMapping("/admin/repository")
    public String repo(HttpServletRequest request, Model model){

        request.setAttribute("path","repository");
        model.addAttribute("repo",repositoryService.selectAll());
        return "repo";
    }

    @RequestMapping("/admin/toAddWaste")
    public String toAdd(){
        return "addWaste";
    }

    @RequestMapping("/admin/addWaste")
    public String add(Repository repository){
        repositoryService.addWaste(repository);
        return "redirect:/admin/repository";
    }

    @RequestMapping("/admin/delete")
    public String delete(String wasteID){
        System.out.println(wasteID);
        repositoryService.deleteWasteByID(wasteID);
        return "redirect:/admin/repository";
    }

    @RequestMapping("/admin/toUpdate")
    public String toUpdate(String wasteID,Model model){
        Repository waste = repositoryService.queryWasteByID(wasteID);
        model.addAttribute("waste",waste);
        return "updateWaste";
    }

    @RequestMapping("/admin/updateWaste")
    public String update(Repository repository){
        repositoryService.updateWasteByID(repository);
        return "redirect:/admin/repository";
    }

    @RequestMapping("/admin/queryWaste")
    public String query(String wasteName,Model model){
        List<Repository> repositoryList = repositoryService.queryWasteByName(wasteName);
        model.addAttribute("repo",repositoryList);
        return "repo";
    }
}
