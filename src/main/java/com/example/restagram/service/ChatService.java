package com.example.restagram.service;

import com.example.restagram.domain.chatroom.ChatRoom;
import com.example.restagram.domain.chatroom.ChatRoomRepository;
import com.example.restagram.web.dto.ChattingListResponseDto;
import com.example.restagram.web.dto.ChattingRoomResponseDto;
import com.example.restagram.web.dto.RequestCreateChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public Long Create(RequestCreateChatRoom requestDto) {
        requestDto.setRoomId(UUID.randomUUID().toString());
        System.out.println(">>>>>>>>>>>>>>>>>>>>." + requestDto.getRoomId());
        return chatRoomRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<ChattingListResponseDto> findAllAsc() {
        return chatRoomRepository.findAllAsc().stream().map(ChattingListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public ChattingRoomResponseDto select(String id) {
        ChatRoom chatRoom = chatRoomRepository.findByRoomId(id);
//        System.out.println(">>>>>>>>>>>>>>>>>>>방 조회." + chatRoom.getId());// 널이면 오류
        return new ChattingRoomResponseDto(chatRoom);
    }

}
