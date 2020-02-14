package com.lytw13.demo.service;

import com.lytw13.demo.model.BaseResult;
import com.lytw13.demo.model.TbJob;

import java.util.List;

/**
 * 定时任务调度表(TbJob)表服务接口
 *
 * @author makejava
 * @since 2020-02-13 13:56:47
 */
public interface TbJobService {

    /**
     * 通过ID查询单条数据
     *
     * @param jobId 主键
     * @return 实例对象
     */
    TbJob queryById(Integer jobId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbJob> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tbJob 实例对象
     * @return 实例对象
     */
    BaseResult insert(TbJob tbJob);

    /**
     * 修改数据
     *
     * @param tbJob 实例对象
     * @return 实例对象
     */
    BaseResult update(TbJob tbJob);

    /**
     * 通过主键删除数据
     *
     * @param jobId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer jobId);

    BaseResult delete(TbJob tbJob);

    BaseResult list(TbJob tbJob);

    BaseResult pause(TbJob tbJob);

    BaseResult resume(TbJob tbJob);

    BaseResult pauseAll();

    BaseResult resumeAll();
}