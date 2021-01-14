package com.example.restagram.web;

import com.example.restagram.config.LoginUser;
import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.users.SessionUser;
import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.users.UsersRepository;
import com.example.restagram.service.PostsService;
import com.example.restagram.service.UserService;
import com.example.restagram.web.postDto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/posts")
@RequiredArgsConstructor
@Controller
public class PostsController {
    private final PostsService postsService;
    private final UsersRepository usersRepository;

    //게시물 작성 페이지로 이동
    @GetMapping("")
    public String write(@LoginUser SessionUser sessionUser){
        if(sessionUser == null)
            return "login";//아직 예외 페이지 처리가 안되어 있어 일단 로그인 페이지로 가도돍 함;
        return "new_post";
    }

    //게시물 상세보기 페이지
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, @LoginUser SessionUser sessionUser, Model model){
       if(sessionUser == null)
           return "loginForm";
        Users users =usersRepository.findByUsername(sessionUser.getUsername()).get();
        PostsResponseDto posts =  postsService.findByid(id, users);
        model.addAttribute("post",posts);
        model.addAttribute("isSameUser", posts.getUser().equals(users));
        return "detail-page";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable Long id, @LoginUser SessionUser sessionUser,Model model){
        if(sessionUser==null)
            return "loginForm";
        model.addAttribute("post", postsService.findByid(id, usersRepository.findByUsername(sessionUser.getUsername()).get()));
        return "update-post";
    }
}
