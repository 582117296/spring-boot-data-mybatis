package com.gjt.springboot.controller;

import com.gjt.springboot.mapper.UserMapper;
import com.gjt.springboot.pojo.User;
import com.gjt.springboot.supplier.GitHubSupplier;
import com.gjt.springboot.vo.AccessTokenVo;
import com.gjt.springboot.vo.GitHubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GitHubSupplier gitHubSupplier;
    @Autowired
    private AccessTokenVo accessTokenVo;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpSession session){
        //AccessTokenVo accessTokenVo = new AccessTokenVo();
        accessTokenVo.setCode(code);
        //accessTokenVo.setClient_id("f4684bceff87cd6e648a");
        //accessTokenVo.setRedirect_uri("http://localhost:8080");
        accessTokenVo.setState(state);
        //accessTokenVo.setClient_secret("b8b3e5a9f4ee607778451c63115300d52dbf2250");
        System.out.println(accessTokenVo.toString());
        String accessToken = gitHubSupplier.getAccessToken(accessTokenVo);
        GitHubUser gitHubUser = gitHubSupplier.getUser(accessToken);
        //System.out.println(user.getName());
        if (gitHubUser != null){
            //登录成功
            User user = new User();
            user.setName(gitHubUser.getName());
            user.setToken(UUID.randomUUID().toString());
            user.setAccount_Id(String.valueOf(gitHubUser.getId()));
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            int addUser = userMapper.addUser(user);
            if (addUser>0){
                session.setAttribute("user", gitHubUser);
                return "redirect:/";
            }
        }else {
            //登录失败
            return "redirect:/";
        }
    }


}
