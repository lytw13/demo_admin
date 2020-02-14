package com.lytw13.demo.mapper.quarz;

import com.lytw13.demo.model.TbJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 定时任务调度表(TbJob)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-13 13:56:46
 */
@Mapper
public interface TbJobMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param jobId 主键
     * @return 实例对象
     */
    TbJob queryById(Integer jobId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbJob> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tbJob 实例对象
     * @return 对象列表
     */
    List<TbJob> queryAll(TbJob tbJob);

    /**
     * 新增数据
     *
     * @param tbJob 实例对象
     * @return 影响行数
     */
    int insert(TbJob tbJob);

    /**
     * 修改数据
     *
     * @param tbJob 实例对象
     * @return 影响行数
     */
    int update(TbJob tbJob);

    /**
     * 通过主键删除数据
     *
     * @param jobId 主键
     * @return 影响行数
     */
    int deleteById(Integer jobId);

    Integer updateAllStatus(Integer jobStatus);
}