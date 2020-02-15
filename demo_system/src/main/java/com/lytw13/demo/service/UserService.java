package com.lytw13.demo.service;

import com.lytw13.demo.model.BaseResult;
import com.lytw13.demo.model.TbUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/user")
public interface UserService {
    @PostMapping("/regist")
    public BaseResult regist(@RequestBody TbUser user);
    @PostMapping("/login")
    public BaseResult login(@RequestBody TbUser user);
    @GetMapping("/getByToken/{token}")
    public BaseResult  getByToken(@PathVariable("token") String token);
    @GetMapping("/get/{id}")
    public BaseResult get(@PathVariable("id") Integer id);
    @GetMapping("/list")
    public BaseResult list(TbUser user);
    @GetMapping("/listByDept")
    public BaseResult listByDept(List deptIds);
    @GetMapping("/delete/{id}")
    public BaseResult delete(@PathVariable("id") Integer id);
    @PostMapping("/updateUser")
    public BaseResult update(@RequestBody TbUser tbUser);
    @PostMapping("/insert")
    public BaseResult insert(@RequestBody TbUser tbUser);
    @PostMapping("/totalBySex/{id}")
    BaseResult getTotalBySex(@PathVariable("id")Integer user_status);
    @PostMapping("/updateTbUser")
    public BaseResult updateUser(@RequestBody TbUser tbUser);

    BaseResult getTotal();

    BaseResult updateByName(TbUser user);
}
