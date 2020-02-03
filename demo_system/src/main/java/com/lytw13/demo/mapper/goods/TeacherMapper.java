package com.lytw13.demo.mapper.goods;

import com.lytw13.demo.model.TbTeacher;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TeacherMapper {
    TbTeacher selectByPrimaryKey(Integer id);
}
