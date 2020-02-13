package com.lytw13.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("swagger")
public class SwaggerController
{
    @GetMapping("/swagger-ui")
    public String index()
    {
        return "redirect:/swagger-ui.html";
    }
}
