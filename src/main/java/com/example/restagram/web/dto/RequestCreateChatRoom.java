package com.example.restagram.web.dto;

import com.example.restagram.domain.chatroom.ChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RequestCreateChatRoom {
    private String name;
    private String roomId;

    @Builder
    public RequestCreateChatRoom(String name) {
        this.name = name;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public ChatRoom toEntity(){
        return ChatRoom.builder().name(name).roomId(roomId).build();
    }

}
