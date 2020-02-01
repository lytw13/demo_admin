package com.lytw13.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lytw13.demo.model.*;
import com.lytw13.demo.service.DictDataService;
import com.lytw13.demo.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("dict")
public class DictController {
    @Autowired
    DictTypeService dictTypeService;
    @Autowired
    DictDataService dictDataService;

    @GetMapping(value="list")
    @ResponseBody
    public PageInfo<TbUser> list(Model model, PageVo pageVo, TbDictType tbDictType) {
        PageHelper.startPage(pageVo.getPageNumber(),pageVo.getPageSize());
        ArrayList resultData = (ArrayList) listDict(model,tbDictType);
        if(resultData == null) {
            resultData = new ArrayList();
        }
        PageInfo<TbUser> pageInfo = new PageInfo<TbUser>(resultData);
        return pageInfo;
    }


    @PostMapping("add")
    public String add(Model model, TbDictType tbDictType) {
        BaseResult result = dictTypeService.insert(tbDictType);
        if(result.getResultCode()!=200){
            model.addAttribute("message",result.getResultMsg());
        }
        return "/dict/dictList";
    }

    @GetMapping("delete")
    public String delete(Model model,Integer dictTypeId) {
        BaseResult result = dictTypeService.delete(dictTypeId);
        if(result.getResultCode()!= 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        return "/dict/dictList";
    }


    @PostMapping("modify")
    public String modify(Model model, TbDictType tbDictType) {
        BaseResult result = dictTypeService.update(tbDictType);
        if(result.getResultCode() != 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        return "/dict/dictList";
    }

    @GetMapping("/listDataPage")
    public String listDataPage(Model model, TbDictType tbDictType) {
        model.addAttribute("tbDictType",tbDictType);
        return "/dict/dictDataList";
    }


    @GetMapping(value="itemsList")
    @ResponseBody
    public PageInfo<TbUser> itemsList(Model model, PageVo pageVo, TbDictData tbDictData) {
        PageHelper.startPage(pageVo.getPageNumber(),pageVo.getPageSize());
        ArrayList resultData = (ArrayList) listDictItems(model,tbDictData);
        if(resultData == null) {
            resultData = new ArrayList();
        }
        PageInfo<TbUser> pageInfo = new PageInfo<TbUser>(resultData);
        return pageInfo;
    }


    @PostMapping("addData")
    public String addData(Model model, TbDictData tbDictData) {
        BaseResult result = dictDataService.insert(tbDictData);
        if(result.getResultCode()!=200){
            model.addAttribute("message",result.getResultMsg());
        }
        TbDictType tbDictType = new TbDictType();
        tbDictType.setDictTypeType(tbDictData.getDictDataType());
        model.addAttribute("tbDictType",tbDictType);
        return "/dict/dictDataList";
    }

    @GetMapping("deleteData")
    public String deleteData(Model model,Integer dictDataId,TbDictType tbDictType) {
        BaseResult result = dictDataService.delete(dictDataId);
        if(result.getResultCode()!= 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        model.addAttribute("tbDictType",tbDictType);
        return "/dict/dictDataList";
    }


    @PostMapping("modifyData")
    public String modifyData(Model model, TbDictData tbDictData) {
        BaseResult result = dictDataService.update(tbDictData);
        if(result.getResultCode() != 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        TbDictType tbDictType = new TbDictType();
        tbDictType.setDictTypeType(tbDictData.getDictDataType());
        model.addAttribute("tbDictType",tbDictType);
        return "/dict/dictDataList";
    }





    public List listDict(Model model, TbDictType tbDictType) {
        BaseResult result = dictTypeService.list(tbDictType);
        if(result.getResultCode() != 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        ArrayList resultData = (ArrayList) result.getResultData();
        return resultData;
    }


    public List listDictItems(Model model, TbDictData tbDictData) {
        BaseResult result = dictDataService.list(tbDictData);
        if(result.getResultCode() != 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        ArrayList resultData = (ArrayList) result.getResultData();
        return resultData;
    }
}
