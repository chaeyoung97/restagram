package com.example.restagram.web.chatDto;

import com.example.restagram.domain.ChatMessage.ChatMessage;
import com.example.restagram.domain.tables.ChatRoomTable;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@RequiredArgsConstructor
public class RequestCreateChatMessageDto {
    private String roomId;
    private String sender;
    private String message;

    private ChatRoomTable chatRoomTable;
    @Builder
    public RequestCreateChatMessageDto(String roomid, String sender, String message) {
        this.roomId = roomid;
        this.sender = sender;
        this.message = message;
    }
    public ChatMessage toEntity()
    {
        return ChatMessage.builder().chatRoomTable(chatRoomTable).message(message)
                .sender(sender).build();
    }

    @Override
    public String toString() {
        return "RequestCreateChatMessageDto{" +
                "roomId='" + roomId + '\'' +
                ", sender='" + sender + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
