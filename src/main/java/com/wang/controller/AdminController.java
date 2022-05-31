package com.wang.controller;

import com.wang.entity.AdminUser;
import com.wang.service.AdminUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    @Resource
    private AdminUserService adminUserService;

    @RequestMapping("/ccc")
    public String admin(){
        return "login";
    }

    @RequestMapping("/admin/index")
    public String index(HttpServletRequest request){
        request.setAttribute("path","index");
        return "index";
    }

    @PostMapping("/admin/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("passWord") String passWord,
                        @RequestParam("verifyCode") String verifyCode,
                        HttpSession session) {
        if (StringUtils.isEmpty(verifyCode)) {
            session.setAttribute("errorMsg", "验证码不能为空");
            return "login";
        }
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return "login";
        }
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            session.setAttribute("errorMsg", "验证码错误");
            return "login";
        }
        AdminUser adminUser = adminUserService.login(userName, passWord);
        if (adminUser != null) {
            session.setAttribute("loginUser", adminUser.getNickName());
            session.setAttribute("loginUserID", adminUser.getUserID());
            return "redirect:/admin/index";
        } else {
            session.setAttribute("errorMsg", "登录信息错误");
            return "login";
        }
    }

}
