package com.example.restagram.service;

import com.example.restagram.domain.ChatMessage.ChatMessage;
import com.example.restagram.domain.ChatMessage.ChatMessageRepository;
import com.example.restagram.domain.tables.ChatRoomTable;
import com.example.restagram.domain.tables.ChatRoomTableRepository;
import com.example.restagram.web.chatDto.MessageListResponseDto;
import com.example.restagram.web.chatDto.RequestCreateChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomTableRepository chatRoomTableRepository;
    @Transactional
    public Long CreateMessage(RequestCreateChatMessageDto messageDto)
    {
        ChatRoomTable c=chatRoomTableRepository.findByRoomId(messageDto.getRoomId());
        messageDto.setChatRoomTable(c);
        return chatMessageRepository.save(messageDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<MessageListResponseDto> findAll(String roomId)
    {
        ChatRoomTable chatRoomTable=chatRoomTableRepository.findByRoomId(roomId);
        List<MessageListResponseDto> arr=chatMessageRepository.findByChatRoomTable(chatRoomTable)
                .stream().map(MessageListResponseDto::new).collect(Collectors.toList());
        if(!arr.isEmpty())
            System.out.println(" >>>>>>>>>>>>>>> "+arr.get(0).getSender()+" >>>> "+arr.get(0).getMessage()+" >>>> "+arr.get(0).getRoomId().getRoomId());
        return arr;
    }
}
