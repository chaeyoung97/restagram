package com.example.restagram.domain.chatroom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChatRoomService {
    private final chatRoomRepository chatRoomRepository;

    @Transactional
    public Long creatChatting()
    {
     return Long.valueOf(1);
    }

}
