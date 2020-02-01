package com.lytw13.demo.dao;

import com.lytw13.demo.model.TbDictData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictDataMapper {
    List<TbDictData> get(TbDictData tbDictData);

    Integer deleteByPrimaryKey(Integer dictDataId);

    Integer updateByPrimaryKeySelective(TbDictData tbDictData);

    int insert(TbDictData tbDictData);
}
