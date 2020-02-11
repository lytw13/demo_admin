package com.lytw13.demo.controller;

import com.lytw13.demo.model.BaseResult;
import com.lytw13.demo.model.TbUser;
import com.lytw13.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserService userService;
    List list = new ArrayList<>();

    @GetMapping(value = "index")
    public String index(HttpSession session) {
        if(session.getAttribute("SESSION_USER")==null) {
            return "redirect:loginPage";
        }
        return "index";
    }



    @PostMapping(value = "login")
    public String login(Model model, TbUser tbUser, String rememberMe) {
        tbUser.setStatus(1);
//        BaseResult login = userService.login(tbUser);
//        if(login.getResultCode()!=200) {
//            model.addAttribute("message",login.getResultMsg());
//            return "login";
//        }
//        session.setAttribute("SESSION_USER",tbUser);
//        return "index";
        boolean flag = true;
        if (rememberMe == null) {
            flag = false;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(tbUser.getName(), tbUser.getPassword(),flag);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AccountException ae) {
            model.addAttribute("message",ae.getMessage());
            return "login";
        }
        subject.getSession().setAttribute("SESSION_USER",tbUser);
        return "index";
    }

    @PostMapping(value="regist")
    public String regist(Model model,TbUser tbUser) {
        BaseResult regist = userService.regist(tbUser);
        if(regist.getResultCode()!=200) {
            model.addAttribute("message",regist.getResultMsg());
            return "regist";
        }
        return "success01";
    }

//    @GetMapping(value = "logout")
//    public String logout(HttpSession session) {
//        session.removeAttribute("SESSION_USER");
//        return "redirect:loginPage";
//    }
}
