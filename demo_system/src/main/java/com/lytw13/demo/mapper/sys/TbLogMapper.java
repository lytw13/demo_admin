package com.lytw13.demo.mapper.sys;

import com.lytw13.demo.model.TbLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 日志表(TbLog)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-12 13:17:22
 */
@Mapper
public interface TbLogMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param logId 主键
     * @return 实例对象
     */
    TbLog queryById(Integer logId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tbLog 实例对象
     * @return 对象列表
     */
    List<TbLog> queryAll(TbLog tbLog);

    /**
     * 新增数据
     *
     * @param tbLog 实例对象
     * @return 影响行数
     */
    int insert(TbLog tbLog);

    /**
     * 修改数据
     *
     * @param tbLog 实例对象
     * @return 影响行数
     */
    int update(TbLog tbLog);

    /**
     * 通过主键删除数据
     *
     * @param logId 主键
     * @return 影响行数
     */
    int deleteById(Integer logId);

}