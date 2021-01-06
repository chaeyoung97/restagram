package com.example.restagram.web;
import com.example.restagram.config.LoginUser;
import com.example.restagram.config.PrincipalDetail;
import com.example.restagram.domain.users.SessionUser;
import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.users.UsersRepository;
import com.example.restagram.service.ImageService;
import com.example.restagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
@RequiredArgsConstructor
@Controller
public class IndexController {
    private final UsersRepository usersRepository;
    private final UserService userService;
    private final ImageService imageService;

    // 기본 home
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser sessionUser, @AuthenticationPrincipal PrincipalDetail userDetails)
    {
        if(sessionUser == null)
        {
            return "login"; //로그인이 되어있지 않다면 로그인화면으로 이동
        }
        System.out.println(usersRepository.findById(userDetails.getUser().getId()).get().getPosts().size());
        model.addAttribute("user", usersRepository.findById(userDetails.getUser().getId()).get());
        return "index";  //로그인이 되어있다면 home화면으로 이동
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
    public String write(@LoginUser SessionUser sessionUser){
        if(sessionUser == null)
            return "login";//아직 예외 페이지 처리가 안되어 있어 일단 로그인 페이지로 가도돍 함;
        return "new_post";
    }
    //프로필 페이지로 이동
    @GetMapping("/profile")
    public String profile(@LoginUser SessionUser sessionUser, Model model){
        if(sessionUser == null)
            return "login";
        Users users = usersRepository.findByUsername(sessionUser.getUsername()).get();
        model.addAttribute("user", users);
        imageService.getProfileImage(users.getId(), model);
        return "profile";
    }

    /*
      아래는 테스트용 api
      테스트완료 후 삭제할 예정
   */
    @Transactional
    @GetMapping("/get/{id}/")
    public @ResponseBody Long image(@PathVariable Long id){
        Users users = usersRepository.findById(id).get();
        return new Long(users.getPosts().size());
    }

    @Transactional
    @GetMapping("/get/user/{id}/")
    public @ResponseBody
    String follw(@PathVariable Long id){
        Users users = usersRepository.findById(id).get();
        return "팔로잉: " + users.getFollower().size() + "팔로워: " + users.getFollowing().size();
    }
}