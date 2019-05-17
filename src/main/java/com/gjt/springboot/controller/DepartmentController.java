package com.gjt.springboot.controller;

import com.gjt.springboot.mapper.UserMapper;
import com.gjt.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class DepartmentController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String getGreeting(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        User user=null;
        for (Cookie cookie : cookies) {
            if (cookie.equals("token")) {
                String token = cookie.getValue();
                user = userMapper.findToken(token);
                break;
            }
        }
        if (user!=null){
            request.getSession().setAttribute("user", user);
        }
        return "index";
    }

}
