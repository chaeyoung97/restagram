package com.example.restagram.domain.chatroom;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@ToString
@Getter
@Entity
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "roomId")
    private String roomId;;

    @Column(nullable = true)
    private String name; // 대화방 상대 이름으로 저장.

}
