package com.gjt.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DepartmentController {

    @GetMapping("/")
    public String getGreeting(){
        return  "index";
    }

}