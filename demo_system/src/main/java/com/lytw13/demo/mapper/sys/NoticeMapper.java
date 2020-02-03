package com.lytw13.demo.mapper.sys;

import com.lytw13.demo.model.TbNotice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(TbNotice tbNotice);

    TbNotice selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(TbNotice tbNotice);

    List<TbNotice> list();

    List<TbNotice> getNotice(TbNotice tbNotice);
}
