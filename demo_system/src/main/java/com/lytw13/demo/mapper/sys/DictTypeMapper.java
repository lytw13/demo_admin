package com.lytw13.demo.mapper.sys;

import com.lytw13.demo.model.TbDictType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictTypeMapper {
    List<TbDictType> list(TbDictType tbDictType);

    int insert(TbDictType tbDictType);

    Integer deleteByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(TbDictType tbDictType);
}
