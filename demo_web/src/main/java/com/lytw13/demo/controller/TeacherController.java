package com.lytw13.demo.controller;

import com.lytw13.demo.model.TbTeacher;
import com.lytw13.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @GetMapping("get/{id}")
    public TbTeacher get(@PathVariable Integer id) {
        TbTeacher tbTeacher = teacherService.get(id);
        return tbTeacher;
    }
}
