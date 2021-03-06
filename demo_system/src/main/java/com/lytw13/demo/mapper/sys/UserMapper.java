package com.lytw13.demo.mapper.sys;

import com.lytw13.demo.model.TbUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(TbUser tbUser);

    TbUser selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(TbUser tbUser);

    List<TbUser> list(TbUser user);

    List<TbUser> selectByDept(List deptIds);

    List selectUserPermission(Integer id);

    Integer getTotalBySex(Integer user_status);

    Integer update(TbUser tbUser);

    Integer updateByName(TbUser tbUser);

    Integer account();
}