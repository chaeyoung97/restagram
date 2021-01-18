package com.example.restagram.web;

import com.example.restagram.config.LoginUser;
import com.example.restagram.domain.tables.ChatRoomTable;
import com.example.restagram.domain.tables.ChatRoomTableRepository;
import com.example.restagram.domain.users.SessionUser;
import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.users.UsersRepository;
import com.example.restagram.service.ChatService;
import com.example.restagram.web.chatDto.RequestCreateChatRoom;
import lombok.RequiredArgsConstructor;
import org.h2.engine.Session;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChattingApiController {

    private final ChatService chatService;
    private final UsersRepository usersRepository;
    private final ChatRoomTableRepository chatRoomTableRepository;
    @PostMapping("/room")
    public Long createRoom(@RequestBody RequestCreateChatRoom requestDto,@LoginUser SessionUser sessionUser)
    {
        // 방이 이미 만들어졌으면 안말들어짐.
        String response=requestDto.getResponseUserId();
        String request=sessionUser.getUsername();
        Users requestUser=usersRepository.findByUsername(request).get();
        Users responseUser=usersRepository.findByUsername(response).get();
        Optional<ChatRoomTable> check1=chatRoomTableRepository.findByRequestUserIdAndResponseUserId(requestUser,responseUser);
        Optional<ChatRoomTable> check2=chatRoomTableRepository.findByRequestUserIdAndResponseUserId(responseUser,requestUser);
        System.out.println(check1.isPresent() +">>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+check2.isPresent());

        if(check1.isPresent() || check2.isPresent())
        {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 중복된 채팅방이 잇습니다.");
            return 0L;
        }
       /* System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "
                +requestDto.toString());
        System.out.println("requestUser >>>>>>>>>>>>>> "+sessionUser.getUsername());
        System.out.println(requestDto.toString());*/
        return chatService.Create(requestDto,sessionUser);
    }
}
