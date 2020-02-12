package com.lytw13.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.lytw13.demo.annotation.Log;
import com.lytw13.demo.model.BaseResult;
import com.lytw13.demo.model.TbMenu;
import com.lytw13.demo.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("menu")
public class MenuController {
    @Autowired
    MenuService menuService;
    

    @GetMapping(value="addPage")
    public String addPage(Model model,@RequestParam(required = false) Integer pid,String menuPname) {
        model.addAttribute("pid",pid);
        model.addAttribute("menuPname",menuPname);
        return "menu/menuAddForm";
    }

    @GetMapping("modifyPage")
    public String modifyPage(Model model, Integer id) {
        BaseResult result = menuService.get(id);
        if(result.getResultCode() != 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        TbMenu menu = (TbMenu) result.getResultData();
        model.addAttribute("menu",menu);
        return "/menu/menuModifyForm";
    }

    @Log("添加菜单")
    @RequiresPermissions("menu:add")
    @PostMapping("add")
    public String add(Model model, TbMenu tbMenu) {
        BaseResult result = menuService.insert(tbMenu);
        if(result.getResultCode()!=200){
            model.addAttribute("message",result.getResultMsg());
        }
        return "/menu/menuList";
    }

    @Log("修改菜单")
    @RequiresPermissions("menu:modify")
    @PostMapping("update")
    public String update(Model model, TbMenu tbMenu) {
        BaseResult result = menuService.update(tbMenu);
        if(result.getResultCode() != 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        return "/menu/menuList";
    }
    @Log("删除菜单")
    @RequiresPermissions("menu:delete")
    @GetMapping("delete")
    public String delete(Model model,Integer id) {
        BaseResult result = menuService.delete(id);
        if(result.getResultCode()!= 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        return "/menu/menuList";
    }

    @GetMapping("list")
    @ResponseBody
    public List listMenu(Model model) {
        List<JSONObject> jsonList = new ArrayList<JSONObject>();
        BaseResult result = menuService.listMenu();
        if(result.getResultCode()!=200) {
            model.addAttribute("message",result.getResultMsg());
        }
        ArrayList<TbMenu> list = (ArrayList) result.getResultData();
        return list;
    }
}
