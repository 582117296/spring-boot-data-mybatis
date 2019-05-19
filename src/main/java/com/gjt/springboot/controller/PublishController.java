package com.gjt.springboot.controller;

import com.gjt.springboot.mapper.QuestionMapper;
import com.gjt.springboot.mapper.UserMapper;
import com.gjt.springboot.pojo.Question;
import com.gjt.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            HttpServletRequest request){
        System.out.println("1");
        User user = null;
        if (request.getCookies()!=null){
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    user=userMapper.queryUserByToken(token);
                    if (user!=null){
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if (user==null){
            request.getSession().setAttribute("msg", "用户未登录,请先登录!");
        }else {
            System.out.println("2");
        Question question=new Question();
        question.setTitle(title);
        question.setTag(tag);
        question.setDescription(description);
        question.setCreate(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModifiled(question.getGmtCreate());
        int num=questionMapper.insertQuestion(question);
        if (num<0){
            System.out.println("3");
            request.getSession().setAttribute("error", "发布失败!");
        }else {
            System.out.println("4");
            request.getSession().setAttribute("success", "发布成功");
            return "redirect:/";
        }
        }
        System.out.println("5");
        return "publish";
    }
}
