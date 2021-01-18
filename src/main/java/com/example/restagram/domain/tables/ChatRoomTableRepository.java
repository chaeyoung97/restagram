package com.example.restagram.domain.tables;

import com.example.restagram.domain.users.Users;
import com.example.restagram.service.ChatService;
import org.h2.engine.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;


public interface ChatRoomTableRepository extends JpaRepository<ChatRoomTable,Long> {

//    ChatRoomTable findByNumber(String Number);


    // 기존 채팅방 있는 지확인하기 위한 용도
    Optional<ChatRoomTable> findByRequestUserIdAndResponseUserId(Users requestUserId, Users responseUserId);

    List<ChatRoomTable>findByRequestUserId(Users requestUserId);
    List<ChatRoomTable>findByResponseUserId(Users responseUserId);

    ChatRoomTable findByRoomId(String roomId);
}
