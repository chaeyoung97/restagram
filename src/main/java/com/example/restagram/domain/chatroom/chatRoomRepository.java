package com.example.restagram.domain.chatroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;


public interface chatRoomRepository extends JpaRepository<ChatRoom,Long> {
}
