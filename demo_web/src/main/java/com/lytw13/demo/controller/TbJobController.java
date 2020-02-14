package com.lytw13.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lytw13.demo.annotation.Log;
import com.lytw13.demo.model.*;
import com.lytw13.demo.service.TbJobService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 定时任务调度表(TbJob)表控制层
 *
 * @author makejava
 * @since 2020-02-13 13:56:49
 */
@Controller
@RequestMapping("job")
public class TbJobController {

    public static TbJobController tbJobController;

    /**
     * 服务对象
     */
    @Resource
    private TbJobService tbJobService;



    /**
     * 初始化当前类
     */
    @PostConstruct
    public void init() {
        tbJobController = this;
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ResponseBody
    @GetMapping("selectOne")
    public TbJob selectOne(Integer id) {
        System.out.println("查询一个selectOne================");
        return tbJobController.tbJobService.queryById(id);
    }

    @ResponseBody
    @GetMapping("selectAll")
    public List<TbJob> selectAll() {
        System.out.println("查询所有================");
        return tbJobController.tbJobService.queryAllByLimit(0,5);
    }

    @GetMapping(value="listJob")
    @ResponseBody
    public PageInfo<TbJob> list(Model model, PageVo pageVo, TbJob tbJob) {
        PageHelper.startPage(pageVo.getPageNumber(),pageVo.getPageSize());
        ArrayList resultData = (ArrayList) listJob(model,tbJob);
        if(resultData == null) {
            resultData = new ArrayList();
        }
        PageInfo<TbJob> pageInfo = new PageInfo<TbJob>(resultData);
        return pageInfo;
    }

    public List listJob(Model model, TbJob tbJob) {
        BaseResult result = tbJobService.list(tbJob);
        if(result.getResultCode() != 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        ArrayList resultData = (ArrayList) result.getResultData();
        return resultData;
    }
    
    /**
     * 新增
     */
    @ApiOperation("新增定时任务")
    @Log("新增定时任务")
    @PostMapping("add")
    @Transactional
    public String add(Model model, TbJob tbJob) throws Exception {
        BaseResult result = tbJobService.insert(tbJob);
        return "/job/jobList";
    }
    @Log("删除定时任务")
    @GetMapping("delete")
    public String delete(Model model,TbJob tbJob) {
        BaseResult result = tbJobService.delete(tbJob);
        if(result.getResultCode()!= 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        return "/job/jobList";
    }

    @GetMapping("pause")
    public String pause(Model model,TbJob tbJob) {
        tbJob.setJobStatus("0");
        BaseResult result = tbJobService.pause(tbJob);
        if(result.getResultCode()!= 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        return "/job/jobList";
    }

    @GetMapping("pauseAll")
    public String pauseAll(Model model) {
        BaseResult result = tbJobService.pauseAll();
        if(result.getResultCode()!= 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        return "/job/jobList";
    }

    @GetMapping("resumeAll")
    public String resumeAll(Model model) {
        BaseResult result = tbJobService.resumeAll();
        if(result.getResultCode()!= 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        return "/job/jobList";
    }



    @GetMapping("resume")
    public String resume(Model model,TbJob tbJob) {
        tbJob.setJobStatus("1");
        BaseResult result = tbJobService.resume(tbJob);
        if(result.getResultCode()!= 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        return "/job/jobList";
    }


    @Log("修改定时任务")
    @PostMapping("update")
    public String update(Model model, TbJob tbJob) {
        BaseResult result = tbJobService.update(tbJob);
        if(result.getResultCode() != 200) {
            model.addAttribute("message",result.getResultMsg());
        }
        return "/job/jobList";
    }

}