package com.lytw13.demo.service.impl;

import com.lytw13.demo.mapper.quarz.TbJobMapper;
import com.lytw13.demo.model.BaseResult;
import com.lytw13.demo.model.TbJob;
import com.lytw13.demo.service.TbJobService;
import com.lytw13.demo.utils.JobUtil;
import com.lytw13.demo.utils.ResponseResult;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 定时任务调度表(TbJob)表服务实现类
 *
 * @author makejava
 * @since 2020-02-13 13:56:48
 */
@Service("tbJobService")
public class TbJobServiceImpl implements TbJobService {

    @Autowired
    JobUtil jobUtil;

    @Resource
    private TbJobMapper tbJobMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param jobId 主键
     * @return 实例对象
     */
    @Override
    public TbJob queryById(Integer jobId) {
        return this.tbJobMapper.queryById(jobId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TbJob> queryAllByLimit(int offset, int limit) {
        return this.tbJobMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tbJob 实例对象
     * @return 实例对象
     */
    @Transactional
    @Override
    public BaseResult insert(TbJob tbJob) {
        Integer result = tbJobMapper.insert(tbJob);
        if(result<=0) {
            return new ResponseResult().setResultFail("新增失败");
        };
        try {
            jobUtil.addJob(tbJob);
        } catch (Exception e) {
            return new ResponseResult().setResultFail("添加定时任务失败");
        }
        return new ResponseResult().setResultSuccess(tbJob);
    }

    /**
     * 修改数据
     *
     * @param tbJob 实例对象
     * @return 实例对象
     */
    @Override
    public BaseResult update(TbJob tbJob) {
        Integer result =  tbJobMapper.update(tbJob);
        if(result == 0) {
            return new ResponseResult().setResultFail("更新失败");
        }
        try {
            jobUtil.modifyJob(tbJob);
        } catch (SchedulerException e) {
            return new ResponseResult().setResultFail("修改定时任务失败");
        }
        return new ResponseResult().setResultSuccess("更新成功");
    }

    /**
     * 通过主键删除数据
     *
     * @param jobId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer jobId) {
        return this.tbJobMapper.deleteById(jobId) > 0;
    }

    @Override
    public BaseResult delete(TbJob tbJob) {
        Integer result = tbJobMapper.deleteById(tbJob.getJobId());
        if(result == 0) {
            return new ResponseResult().setResultFail("删除失败");
        }
        try {
            jobUtil.deleteJob(tbJob);
        } catch (SchedulerException e) {
            return new ResponseResult().setResultFail("更新失败");
        }
        return new ResponseResult().setResultSuccess("删除成功");
    }

    @Override
    public BaseResult list(TbJob tbJob) {
        List<TbJob> logList = tbJobMapper.queryAll(tbJob);
        if(logList.size() == 0) {
            return new ResponseResult().setResultFail("没有查到相应结果");
        }
        return new ResponseResult().setResultSuccess(logList);
    }

    @Override
    public BaseResult pause(TbJob tbJob) {
        Integer result =  tbJobMapper.update(tbJob);
        if(result == 0) {
            return new ResponseResult().setResultFail("更新状态失败");
        }
        try {
            jobUtil.pauseJob(tbJob);
        } catch (SchedulerException e) {
            return new ResponseResult().setResultFail("终止任务失败");
        }
        return new ResponseResult().setResultSuccess("更新成功");
    }

    @Override
    public BaseResult resume(TbJob tbJob) {
        Integer result =  tbJobMapper.update(tbJob);
        if(result == 0) {
            return new ResponseResult().setResultFail("更新状态失败");
        }
        try {
            jobUtil.resumeJob(tbJob);
        } catch (SchedulerException e) {
            return new ResponseResult().setResultFail("恢复任务失败");
        }
        return new ResponseResult().setResultSuccess("更新成功");
    }

    @Override
    public BaseResult pauseAll() {
        Integer result =  tbJobMapper.updateAllStatus(0);
        if(result == 0) {
            return new ResponseResult().setResultFail("更新状态失败");
        }
        try {
            jobUtil.pauseAllJob();
        } catch (SchedulerException e) {
            return new ResponseResult().setResultFail("终止所有任务失败");
        }
        return new ResponseResult().setResultSuccess("终止所有任务成功");
    }

    @Override
    public BaseResult resumeAll() {
        Integer result =  tbJobMapper.updateAllStatus(1);
        if(result == 0) {
            return new ResponseResult().setResultFail("更新状态失败");
        }
        try {
            jobUtil.resumeAllJob();
        } catch (SchedulerException e) {
            return new ResponseResult().setResultFail("恢复所有任务失败");
        }
        return new ResponseResult().setResultSuccess("恢复所有任务成功");
    }
}