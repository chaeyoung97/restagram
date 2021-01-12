package com.example.restagram.web.dto;

import com.example.restagram.domain.chatroom.ChatRoom;
import lombok.Getter;

@Getter
public class ChattingRoomResponseDto {
    private Long id;
    private String roomId;
    private String name;

    public ChattingRoomResponseDto(ChatRoom entity){
        this.id=entity.getId();
        this.roomId=entity.getRoomId();
        this.name=entity.getName();
    }
}
