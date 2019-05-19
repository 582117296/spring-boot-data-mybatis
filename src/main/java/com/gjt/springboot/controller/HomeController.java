package com.gjt.springboot.controller;

import com.gjt.springboot.mapper.UserMapper;
import com.gjt.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String getGreeting(HttpServletRequest request){
        if (request.getCookies()!=null){
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user=userMapper.queryUserByToken(token);
                    if (user!=null){
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        return "index";
    }

}
