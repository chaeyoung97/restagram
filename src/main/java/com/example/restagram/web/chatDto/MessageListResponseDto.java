package com.example.restagram.web.chatDto;

import com.example.restagram.domain.ChatMessage.ChatMessage;
import com.example.restagram.domain.tables.ChatRoomTable;
import lombok.Getter;

@Getter
public class MessageListResponseDto {
    private Long id;
    private ChatRoomTable roomId;
    private String sender;
    private String message;

    public MessageListResponseDto(ChatMessage entity)
    {
        this.id=entity.getId();
        this.roomId=entity.getChatRoomTable();
        this.sender=entity.getSender();
        this.message=entity.getMessage();
    }
}
