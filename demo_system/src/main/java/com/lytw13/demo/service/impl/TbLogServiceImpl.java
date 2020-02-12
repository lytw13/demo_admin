package com.lytw13.demo.service.impl;

import com.lytw13.demo.mapper.sys.TbLogMapper;
import com.lytw13.demo.model.BaseResult;
import com.lytw13.demo.model.TbLog;
import com.lytw13.demo.service.TbLogService;
import com.lytw13.demo.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 日志表(TbLog)表服务实现类
 *
 * @author makejava
 * @since 2020-02-12 13:17:28
 */
@Service("tbLogService")
public class TbLogServiceImpl implements TbLogService {
    @Autowired
    private TbLogMapper tbLogMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param logId 主键
     * @return 实例对象
     */
    @Override
    public TbLog queryById(Integer logId) {
        return this.tbLogMapper.queryById(logId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TbLog> queryAllByLimit(int offset, int limit) {
        return this.tbLogMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tbLog 实例对象
     * @return 实例对象
     */
    @Override
    public TbLog insert(TbLog tbLog) {
        this.tbLogMapper.insert(tbLog);
        return tbLog;
    }

    /**
     * 修改数据
     *
     * @param tbLog 实例对象
     * @return 实例对象
     */
    @Override
    public TbLog update(TbLog tbLog) {
        this.tbLogMapper.update(tbLog);
        return this.queryById(tbLog.getLogId());
    }

    /**
     * 通过主键删除数据
     *
     * @param logId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer logId) {
        return this.tbLogMapper.deleteById(logId) > 0;
    }

    @Override
    public BaseResult list(TbLog tbLog) {
        List<TbLog> logList = tbLogMapper.queryAll(tbLog);
        if(logList.size() == 0) {
            return new ResponseResult().setResultFail("没有查到相应结果");
        }
        return new ResponseResult().setResultSuccess(logList);
    }
}