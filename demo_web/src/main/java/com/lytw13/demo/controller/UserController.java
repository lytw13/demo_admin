package com.lytw13.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lytw13.demo.model.*;
import com.lytw13.demo.service.RoleService;
import com.lytw13.demo.service.UserDeptService;
import com.lytw13.demo.service.UserRoleService;
import com.lytw13.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserDeptService userDeptService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    RoleService roleService;

    @GetMapping("modifyPage")
    public String modifyPage(Model model,Integer id) {
        BaseResult result = userService.get(id);
        if(result.getResultCode() != 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        model.addAttribute("user",result.getResultData());
        return "/user/userModifyForm";
    }


    @GetMapping(value="listUser")
    @ResponseBody
    public PageInfo<TbUser> list(Model model, PageVo pageVo, TbUser tbUser) {
        PageHelper.startPage(pageVo.getPageNumber(),pageVo.getPageSize());
        ArrayList resultData = (ArrayList) listUser(model,tbUser);
        if(resultData == null) {
            resultData = new ArrayList();
        }
        PageInfo<TbUser> pageInfo = new PageInfo<TbUser>(resultData);
        return pageInfo;
    }

    @GetMapping("addPage")
    public String addPage(Model model) {
        BaseResult baseResult = roleService.listRole();
        ArrayList resultData = (ArrayList) baseResult.getResultData();
        model.addAttribute("roles",resultData);
        return "/user/userAddForm";
    }

    @PostMapping("add")
    @Transactional
    public String add(Model model, TbUser tbUser, Integer dept_id,Integer[] role_ids) {
        BaseResult result = userService.insert(tbUser);
        TbUserDept tbUserDept = new TbUserDept();
        TbUser user = (TbUser) result.getResultData();
        tbUserDept.setUid(user.getId());
        tbUserDept.setDid(dept_id);
        userDeptService.insert(tbUserDept);
        TbUserRole userRole = new TbUserRole();
        for (Integer id:
             role_ids) {
            userRole.setUid(user.getId());
            userRole.setRid(id);
            userRoleService.insert(userRole);
        }
        return "/user/userList";
    }

    @GetMapping("delete")
    public String delete(Model model,Integer id) {
        BaseResult result = userService.delete(id);
        if(result.getResultCode()!= 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        return "/user/userList";
    }

    @PostMapping("updateUser")
    public String updateUser(Model model, TbUser tbUser) {
        BaseResult result = userService.update(tbUser);
        if(result.getResultCode() != 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        return "/user/userList";
    }

    @Scheduled(cron = "0 0 16 * * 1-5")
    @RequestMapping("totalBySex")
    @ResponseBody
    public Map<String, Integer> totalBySex(Model model) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        BaseResult result1 = userService.getTotalBySex(0);
        BaseResult result2 = userService.getTotalBySex(1);
        if(result1.getResultCode() != 200) {
            model.addAttribute("message",result1.getResultMsg()+result2.getResultMsg());
        }
        map.put("男", (Integer) result1.getResultData());
        map.put("女", (Integer)result2.getResultData());
        return map;
    }

    @RequestMapping("changeMsg")
    public String changeMsg(Model model,TbUser user) {
        ArrayList<TbUser> list = (ArrayList) listUser(model,user);
        TbUser tbUser = list.get(0);
        model.addAttribute("tbUser",tbUser);
        return "/user/userMessage";
    }


    public List listUser(Model model, TbUser tbUser) {
        BaseResult result = userService.list(tbUser);
        if(result.getResultCode() != 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        ArrayList resultData = (ArrayList) result.getResultData();
        return resultData;
    }

    @PostMapping("changeStatus")
    public String changeStatus(TbUser tbUser) {
        if(tbUser.getStatus() == 1) {
            tbUser.setStatus(0);
        }else {
            tbUser.setStatus(1);
        }
        userService.updateUser(tbUser);
           return "/user/userList";
    }

    @PostMapping("check")
    @ResponseBody
    public Integer check(TbUser tbUser) {
        BaseResult result = userService.list(tbUser);
        if(result.getResultCode()!=200) {
              return 0;
        }
        return 1;
    }

}