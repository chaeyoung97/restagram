package com.example.restagram.web;

import com.example.restagram.config.LoginUser;
import com.example.restagram.domain.users.SessionUser;
import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.users.UsersRepository;
import com.example.restagram.service.PostsService;
import com.example.restagram.service.UserService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, @LoginUser SessionUser sessionUser, Model model){
       if(sessionUser == null)
           return "loginForm";
        model.addAttribute("post", postsService.findByid(id, usersRepository.findByUsername(sessionUser.getUsername()).get()));
        return "detail-page";
    }
}
