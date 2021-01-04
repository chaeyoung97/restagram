package com.example.restagram.web;

import com.example.restagram.config.LoginUser;
import com.example.restagram.domain.users.SessionUser;
import com.example.restagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@RequiredArgsConstructor
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


    private final UserService userService;

    // 관리자만 허용하는 userList
    @GetMapping("/admin/userList")
    public String userList(@LoginUser SessionUser user,Model model){
        System.out.println("여기가 관리자 페이지."); // 관리자의 역할  h2 데이터 베이스 직접 변경
                                                    // update users set ROLE='ADMIN' where username='admin'
        try {
            user.getUsername(); // null 이 발생하면 예외처리 구문으로 Error를 일으킴.
            model.addAttribute("userList",userService.userlist());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "/user/userList";
    }

    //게시물 작성 페이지로 이동
    @GetMapping("/posts")
    public String write(@LoginUser SessionUser user){
        if(user == null)
            return "login";//아직 예외 페이지 처리가 안되어 있어 일단 로그인 페이지로 가도돍 함;
        return "new_post";
    }

    //프로필 페이지로 이동
    @GetMapping("/profile")
    public String profile(@LoginUser SessionUser user){
        if(user == null)
            return "login";
        return "profile";
    }


}