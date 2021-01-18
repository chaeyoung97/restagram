package com.example.restagram.web.chatDto;

import com.example.restagram.domain.tables.ChatRoomTable;
import com.example.restagram.domain.users.Users;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class RequestCreateChatRoom {
    private String roomId;
    private String responseUserId; // 대화 요청 받는 사람.
    private String name;
    private Users requestUser;
    private Users responseUser;

    @Builder
    public RequestCreateChatRoom(String roomId,String responseUserId) {
        this.roomId =roomId;
        this.responseUserId=responseUserId;
    }

    public ChatRoomTable toEntity()
    {
        return ChatRoomTable.builder().RoomId(roomId).name(name).responseUserId(responseUser)
                .requestUserId(requestUser).build();
    }




}
