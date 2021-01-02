package com.example.restagram.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    // 기본 home
    @GetMapping("/")
    public String index()
    {
        return "index";
    }

    //회원가입 페이지
    @GetMapping("/signUpForm")
    public String signUpForm() {
        return "user/signUpForm";
    }

    //로그인 페이지로 이동
    @GetMapping("/loginForm")
    public String loginForm() {
        return "/user/login";
    }

    //팔로우
    @GetMapping("/follow")
    public String follow() {
        return "";
    }

    //팔로워
    @GetMapping("/follower")
    public String follower() {
        return "";
    }

}
