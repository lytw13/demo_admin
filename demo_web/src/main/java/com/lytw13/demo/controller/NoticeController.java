package com.lytw13.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lytw13.demo.model.BaseResult;
import com.lytw13.demo.model.PageVo;
import com.lytw13.demo.model.TbNotice;
import com.lytw13.demo.model.TbUser;
import com.lytw13.demo.service.NoticeService;
import com.lytw13.demo.utils.MapAndModelConverge;
import com.lytw13.demo.utils.ResponseResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping("notice")
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    @RequiresPermissions("notice:add")
    @PostMapping("add")
    public String add(Model model, TbNotice tbNotice) {
        BaseResult result = noticeService.insert(tbNotice);
        if(result.getResultCode()!=200){
            model.addAttribute("message",result.getResultMsg());
        }
        return "/notice/noticeList";
    }

    @RequiresPermissions("notice:modify")
    @PostMapping("updateNotice")
    public String updateNotice(Model model, TbNotice tbNotice) {
        BaseResult result = noticeService.update(tbNotice);
        if(result.getResultCode() != 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        return "/notice/noticeList";
    }

    @RequiresPermissions("notice:delete")
    @GetMapping("delete")
    public String delete(Model model,Integer id) {
        BaseResult result = noticeService.delete(id);
        if(result.getResultCode()!= 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        return "/notice/noticeList";
    }

    @GetMapping("list")
    @ResponseBody
    public PageInfo listNotice(Model model, PageVo pageVo, TbNotice tbNotice) {
        PageHelper.startPage(pageVo.getPageNumber(),pageVo.getPageSize());
        ArrayList resultData = (ArrayList) list(model);
        PageInfo<TbNotice> pageInfo = new PageInfo<TbNotice>(resultData);
        return pageInfo;
    }

    @PostMapping("list")
    @ResponseBody
    public List list(Model model) {
        BaseResult result = noticeService.listNotice();
        if(result.getResultCode()!=200) {
            model.addAttribute("message",result.getResultMsg());
        }
        ArrayList resultData = (ArrayList) result.getResultData();
        return resultData;
    }



    @PostMapping("showNotice")
    @ResponseBody
    public TbNotice showNotice(Model model) {
        TbNotice tbNotice = new TbNotice();
        tbNotice.setStatus(1);
        BaseResult baseResult = noticeService.getNotice(tbNotice);
        if(baseResult.getResultCode()!=200) {
            model.addAttribute("message",baseResult.getResultMsg());
        }
        ArrayList resultData = (ArrayList) baseResult.getResultData();
        if(resultData.size() > 1) {
            model.addAttribute("message","查询结果数量大于1");
        }
        return (TbNotice)resultData.get(0);
    }


    @GetMapping("/modifyPage")
    public String modifyPage(Integer id,Model model) {
        TbNotice notice = getNotice(id, model);
        model.addAttribute("notice",notice);
        return "notice/noticeModifyForm";
    }

    public TbNotice getNotice(Integer id,Model model) {
        BaseResult result = noticeService.get(id);
        if(result.getResultCode() != 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        TbNotice notice = (TbNotice) result.getResultData();
        return notice;
    }

}
