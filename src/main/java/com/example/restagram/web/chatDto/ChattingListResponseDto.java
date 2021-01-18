package com.example.restagram.web.chatDto;

import com.example.restagram.domain.tables.ChatRoomTable;
import lombok.Getter;

@Getter
public class ChattingListResponseDto {
    private Long id;
    private String roomId;
    private String name;

    public ChattingListResponseDto(ChatRoomTable entity)
    {
        this.id=entity.getId();
        this.roomId=entity.getRoomId();
        this.name=entity.getName();
    }

}
