package com.lytw13.demo.service;

import com.lytw13.demo.model.BaseResult;
import com.lytw13.demo.model.TbLog;
import java.util.List;

/**
 * 日志表(TbLog)表服务接口
 *
 * @author makejava
 * @since 2020-02-12 13:17:23
 */
public interface TbLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param logId 主键
     * @return 实例对象
     */
    TbLog queryById(Integer logId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tbLog 实例对象
     * @return 实例对象
     */
    TbLog insert(TbLog tbLog);

    /**
     * 修改数据
     *
     * @param tbLog 实例对象
     * @return 实例对象
     */
    TbLog update(TbLog tbLog);

    /**
     * 通过主键删除数据
     *
     * @param logId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer logId);
    BaseResult list(TbLog tbLog);

}