package com.lytw13.demo.controller;

import com.lytw13.demo.model.BaseResult;
import com.lytw13.demo.model.TbRole;
import com.lytw13.demo.model.TbRoleMenu;
import com.lytw13.demo.model.TbUser;
import com.lytw13.demo.service.RoleMenuService;
import com.lytw13.demo.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    RoleMenuService roleMenuService;

    @GetMapping("modify")
    public String modifyPage(Model model,Integer id) {
        BaseResult result = roleService.get(id);
        if(result.getResultCode() != 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        TbRole role = (TbRole) result.getResultData();
        model.addAttribute("role",role);
        return "/role/roleModifyForm";
    }
    @RequiresPermissions("role:add")
    @Transactional
    @PostMapping("add")
    public String add(Model model, TbRole tbRole,String menuList) {
//        if(ckStatus.equalsIgnoreCase("on")) {
//            tbRole.setStatus(1);
//        }else {
//            tbRole.setStatus(0);
//        }
        BaseResult result = roleService.insert(tbRole);
        if(result.getResultCode()!=200){
            model.addAttribute("message",result.getResultMsg());
        }
        TbRole resultData = (TbRole) result.getResultData();
        TbRoleMenu tbRoleMenu = new TbRoleMenu();
        tbRoleMenu.setRole_id(resultData.getId());
        String[] permissions = menuList.split(",");
        for (String permission:
                permissions) {
            tbRoleMenu.setMenu_id(Integer.parseInt(permission));
            roleMenuService.save(tbRoleMenu);
        }
        return "/role/roleList";
    }

    @RequiresPermissions("role:modify")
    @PostMapping("updateRole")
    public String updateRole(Model model, TbRole tbRole, String menuList) {
        BaseResult result = roleService.update(tbRole);
        if(result.getResultCode() != 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        return "/role/roleList";
    }
    @RequiresPermissions("role:delete")
    @GetMapping("delete")
    public String delete(Model model,Integer id) {
        BaseResult result = roleService.delete(id);
        if(result.getResultCode() != 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        return "/role/roleList";
    }

    @GetMapping("list")
    @ResponseBody
    public List listRole(Model model) {
        BaseResult result = roleService.listRole();
        if(result.getResultCode()!=200) {
            model.addAttribute("message",result.getResultMsg());
        }
        ArrayList list = (ArrayList) result.getResultData();
        return list;
    }

    @PostMapping("changeStatus")
    public String changeStatus(TbRole tbRole) {
        if(tbRole.getStatus() == 1) {
            tbRole.setStatus(0);
        }else {
            tbRole.setStatus(1);
        }
        roleService.updateRole(tbRole);
        return "/role/roleList";
    }

}
