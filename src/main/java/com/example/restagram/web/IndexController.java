package com.example.restagram.web;

import com.example.restagram.config.LoginUser;
import com.example.restagram.domain.users.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    // 기본 home
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user)
    {
        if(user!=null)
        {
            model.addAttribute("member",user.getUsername());
            return "index";  //로그인이 되어있다면 home화면으로 이동
        }

        return "login"; //로그인이 되어있지 않다면 로그인화면으로 이동
    }

    //회원가입 페이지
    @GetMapping("/signUpForm")
    public String signUpForm() {
        return "signUpForm";
    }

    //로그인 페이지로 이동
    @GetMapping("/loginForm")
    public String loginForm() {
        return "login";
    }


}