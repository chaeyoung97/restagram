package com.example.restagram.domain.ChatMessage;

import com.example.restagram.domain.tables.ChatRoomTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {
    List<ChatMessage>findByChatRoomTable(ChatRoomTable chatRoomTable);
}
