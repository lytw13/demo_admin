package com.lytw13.demo.service.impl;

import com.lytw13.demo.mapper.goods.TeacherMapper;
import com.lytw13.demo.model.TbTeacher;
import com.lytw13.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;
    @Override
    public TbTeacher get(Integer id) {
        return teacherMapper.selectByPrimaryKey(id);
    }
}
