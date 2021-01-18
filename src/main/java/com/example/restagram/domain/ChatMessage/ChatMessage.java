package com.example.restagram.domain.ChatMessage;

import com.example.restagram.domain.BaseTimeEntity;
import com.example.restagram.domain.tables.ChatRoomTable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ChatMessage extends BaseTimeEntity {
    public enum MessageType{
        ENTER,TALK
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name="RoomId")
    private ChatRoomTable chatRoomTable;

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id=" + id +
                ", chatRoomTable=" + chatRoomTable.getRoomId()+
                ", sender='" + sender + '\'' +
                ", Message='" + Message + '\'' +
                '}';
    }

    //    private MessageType type;
    private String sender;
    @Column(columnDefinition = "text")
    private String Message;
    @Builder
    public ChatMessage( ChatRoomTable chatRoomTable,String sender, String message) {
        this.chatRoomTable=chatRoomTable;
        this.sender = sender;
        Message = message;
    }
}
