package com.example.restagram.web;
import com.example.restagram.config.LoginUser;
import com.example.restagram.domain.ChatMessage.ChatMessage;
import com.example.restagram.domain.ChatMessage.ChatMessageRepository;
import com.example.restagram.domain.tables.ChatRoomTable;
import com.example.restagram.domain.users.SessionUser;
import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.users.UsersRepository;
import com.example.restagram.service.*;
import com.example.restagram.web.chatDto.ChattingListResponseDto;
import com.example.restagram.web.chatDto.ChattingRoomResponseDto;
import com.example.restagram.web.chatDto.RequestCreateChatMessageDto;
import com.example.restagram.web.postDto.PostsResponseDto;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final UsersRepository usersRepository;
    private final UserService userService;
    private final ImageService imageService;
    private final PostsService postsService;
    private final ChatService chatService;
    private final ChatMessageService chatMessageService;


    // 기본 home
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser sessionUser)
    {
        if(sessionUser == null){
            return "login"; //로그인이 되어있지 않다면 로그인화면으로 이동
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>."+sessionUser.getUsername());
        Users users = usersRepository.findByUsername(sessionUser.getUsername()).get();
        List<PostsResponseDto> postsList = postsService.findAllPostsByMyFollow(users);

        model.addAttribute("users", users);
        model.addAttribute("followPosts", postsList);
        return "index";  //로그인이 되어있다면 home화면으로 이동
    }

    //회원가입 페이지
    @GetMapping("/signUpForm")
    public String signUpForm() {
        return "signUpForm";
    }

    //회원탈퇴 페이지
    @GetMapping("/withdrawal")
    public String deleteMember(@LoginUser SessionUser sessionUser, Model model)
    {
        if(sessionUser==null)
        {
            return "login";
        }
        Users users=usersRepository.findByUsername(sessionUser.getUsername()).get();

        model.addAttribute("users",users);
        return "withdrawal";

    }

    //로그인 페이지로 이동
    @GetMapping("/loginForm")
    public String loginForm() {
        return "login";
    }

    // 관리자만 허용하는 userList
    @GetMapping("/admin")
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
        return "/userList";
    }


    //프로필 페이지로 이동
    @GetMapping("/profile/{username}")
    public String profile(@LoginUser SessionUser sessionUser,@PathVariable String username, Model model){
        if(sessionUser == null)
            return "login";
        Users users = usersRepository.findByUsername(username).get();
        model.addAttribute("user", users);
        model.addAttribute("isSameSessionUser", sessionUser.getUsername().equals(username));
        imageService.getProfileImage(users.getId(), model);
        imageService.getUserImages(users.getId(), model);
        return "profile";
    }
    
    //프로필 편집 페이지 - 작성자: 이득행
    @GetMapping("/edit/profile")
    public String updateProfile(@LoginUser SessionUser sessionUser, Model model) {
    	if(sessionUser==null) return "login";
    	Users users = usersRepository.findByUsername(sessionUser.getUsername()).get();
    	model.addAttribute("user", users);
    	imageService.getProfileImage(users.getId(), model);
    	return "edit_profile";
    }

    @GetMapping("/edit/password")
    public String updatePassword(@LoginUser SessionUser sessionUser, Model model) {
    	if(sessionUser==null) return "login";
    	Users users = usersRepository.findByUsername(sessionUser.getUsername()).get();
    	model.addAttribute("user", users);
    	imageService.getProfileImage(users.getId(), model);
    	return "edit_password";
    }
    // 채팅방 List
    @GetMapping("/room")
    public String room(Model model,@LoginUser SessionUser sessionUser) {

        model.addAttribute("chat", chatService.findAllAsc(sessionUser));
        model.addAttribute("user",sessionUser);
        return "/room";
    }

    // 채팅방 입장.
    @GetMapping("/chat/room/{roomId}")
    public String roomEnter(@PathVariable String roomId,Model model)
    {
        ChattingRoomResponseDto responseDto= chatService.select(roomId);
//       System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>"+responseDto.toString());
        model.addAttribute("message",chatMessageService.findAll(roomId));
        model.addAttribute("room",responseDto);
        return "/roomdetail";
    }
    // pub/sub message sending
    private final SimpMessageSendingOperations messagingTemplate;
    @MessageMapping("/chat/message")
    public void message(RequestCreateChatMessageDto message) {
        Long id=chatMessageService.CreateMessage(message);
        if(id!=null)
        {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  메시지 저장 완료");
            messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message.getMessage());
        }
    }

}