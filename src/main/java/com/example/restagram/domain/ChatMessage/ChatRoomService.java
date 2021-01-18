/*
package com.example.restagram.domain.chatroom;

import com.example.restagram.domain.tables.ChatRoomTable;
import com.example.restagram.domain.tables.ChatRoomTableRepository;
import com.example.restagram.web.chatDto.ChattingListResponseDto;
import com.example.restagram.web.chatDto.ChattingRoomResponseDto;
import com.example.restagram.web.chatDto.RequestCreateChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatRoomService {
    private final ChatRoomTableRepository chatRoomTableRepository;


    @Transactional
    public Long Create(RequestCreateChatRoom requestDto) {
//        requestDto.setRoomId(UUID.randomUUID().toString());
        System.out.println(">>>>>>>>>>>>>>>>>>>>." + requestDto.getRoomId());
        return chatRoomTableRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<ChattingListResponseDto> findAllAsc() {
        return chatRoomTableRepository.findAllAsc().stream().map(ChattingListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public ChattingRoomResponseDto select(String id) {
        ChatRoomTable chatRoomTable = chatRoomTableRepository.findByRoomId(id);
//        System.out.println(">>>>>>>>>>>>>>>>>>>방 조회." + chatRoom.getId());// 널이면 오류
        return new ChattingRoomResponseDto(chatRoomTable);
    }
}
*/
