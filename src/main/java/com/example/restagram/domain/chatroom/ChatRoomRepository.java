package com.example.restagram.domain.chatroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;


public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {
    @Query(value = "select c from ChatRoom c order by c.id asc")
    List<ChatRoom> findAllAsc();

    ChatRoom findByRoomId(String roomId);
}
