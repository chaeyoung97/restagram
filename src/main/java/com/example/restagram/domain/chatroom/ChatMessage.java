package com.example.restagram.domain.chatroom;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ChatMessage {
    public enum MessageType{
        ENTER,TALK
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private MessageType type;
    private String roomId;
    private String sender;
    private String Message;
    @Builder
    public ChatMessage(MessageType type, String roomId, String sender, String message) {
        this.type = type;
        this.roomId = roomId;
        this.sender = sender;
        Message = message;
    }
}
