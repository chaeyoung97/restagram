package com.example.restagram.service;

import com.example.restagram.config.LoginUser;
import com.example.restagram.domain.ChatMessage.ChatMessage;
import com.example.restagram.domain.ChatMessage.ChatMessageRepository;
import com.example.restagram.domain.tables.ChatRoomTable;
import com.example.restagram.domain.tables.ChatRoomTableRepository;
import com.example.restagram.domain.users.SessionUser;
import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.users.UsersRepository;
import com.example.restagram.web.chatDto.ChattingListResponseDto;
import com.example.restagram.web.chatDto.ChattingRoomResponseDto;
import com.example.restagram.web.chatDto.RequestCreateChatRoom;
import lombok.RequiredArgsConstructor;
import org.h2.engine.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRoomTableRepository chatRoomTableRepository;
    private final UsersRepository usersRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Transactional
    public Long Create(RequestCreateChatRoom requestDto, SessionUser sessionUser) {
        requestDto.setRoomId(UUID.randomUUID().toString());

        String request=sessionUser.getUsername(); // 현재 로그인한 사용자 대화 요청자.
        String response=requestDto.getResponseUserId();
        Users requestUser=usersRepository.findByUsername(request).get(); // 현재 로그인한 사용자가 상대방에게 대화요청.
        Users responseUser=usersRepository.findByUsername(response).get();
        requestDto.setName("참가자 : "+sessionUser.getUsername() +" , "+responseUser.getUsername());
        requestDto.setRequestUser(requestUser);
        requestDto.setResponseUser(responseUser);

        return chatRoomTableRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<ChattingListResponseDto> findAllAsc(SessionUser sessionUser) {
        Users request=usersRepository.findByUsername(sessionUser.getUsername()).get();

        List<ChattingListResponseDto> arr=chatRoomTableRepository.findByRequestUserId(request).stream().map(ChattingListResponseDto::new).collect(Collectors.toList());
        arr.addAll(chatRoomTableRepository.findByResponseUserId(request).stream().map(ChattingListResponseDto::new).collect(Collectors.toList()));
        return arr;
    }

    @Transactional(readOnly = true)
    public ChattingRoomResponseDto select(String id) {
        ChatRoomTable chatRoomTable =chatRoomTableRepository.findByRoomId(id);
//        System.out.println(">>>>>>>>>>>>>>>>>>>방 조회." + chatRoom.getId());// 널이면 오류
        return new ChattingRoomResponseDto(chatRoomTable);
    }

////    @Transactional(readOnly = true)
//    public ChatRoomTable messageService(String id)
//    {
//        return chatRoomTableRepository.findByRoomId(id);
//    }

}
