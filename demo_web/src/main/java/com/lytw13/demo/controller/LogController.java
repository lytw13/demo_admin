package com.lytw13.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lytw13.demo.model.BaseResult;
import com.lytw13.demo.model.PageVo;
import com.lytw13.demo.model.TbLog;
import com.lytw13.demo.model.TbUser;
import com.lytw13.demo.service.TbLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("log")
public class LogController {
    @Autowired
    TbLogService tbLogService;
    @GetMapping(value="listLog")
    @ResponseBody
    public PageInfo<TbUser> list(Model model, PageVo pageVo, TbLog tbLog) {
        PageHelper.startPage(pageVo.getPageNumber(),pageVo.getPageSize());
        ArrayList resultData = (ArrayList) listUser(model,tbLog);
        if(resultData == null) {
            resultData = new ArrayList();
        }
        PageInfo<TbUser> pageInfo = new PageInfo<TbUser>(resultData);
        return pageInfo;
    }

    public List listUser(Model model, TbLog tbLog) {
        BaseResult result = tbLogService.list(tbLog);
        if(result.getResultCode() != 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        ArrayList resultData = (ArrayList) result.getResultData();
        return resultData;
    }
}
