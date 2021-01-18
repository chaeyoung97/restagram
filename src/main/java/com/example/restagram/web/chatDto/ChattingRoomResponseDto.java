package com.example.restagram.web.chatDto;

import com.example.restagram.domain.ChatMessage.ChatMessage;
import com.example.restagram.domain.tables.ChatRoomTable;
import com.example.restagram.domain.users.Users;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
public class ChattingRoomResponseDto {
    private Long id;

    @Override
    public String toString() {
        return "ChattingRoomResponseDto{" +
                "id=" + id +
                ", roomId='" + roomId + '\'' +
                ", requestUserId=" + requestUserId.getUsername() +
                ", responseUserId=" + responseUserId.getUsername() +
                ", name='" + name + '\'' +
                '}';
    }

    private String roomId;
    private Users requestUserId;
    private Users responseUserId;
    private String name;

    private List<ChatMessage> chatMessages;

    public ChattingRoomResponseDto(ChatRoomTable entity){
        this.id=entity.getId();
        this.requestUserId=entity.getRequestUserId();
        this.responseUserId=entity.getResponseUserId();
        this.chatMessages=entity.getChatMessages();
        this.name=entity.getName();
        this.roomId=entity.getRoomId();
    }
}
