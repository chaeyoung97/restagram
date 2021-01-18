package com.example.restagram.domain.tables;


import com.example.restagram.domain.BaseTimeEntity;
import com.example.restagram.domain.ChatMessage.ChatMessage;
import com.example.restagram.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class ChatRoomTable extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomId;
// user와 일대다 양방향 매핑. 이 개체는 N으로서 단방향 시 여기에만 명시하지만 양방향이라 User쪽에도 참조가 가능하게 함.
// 일대다 단방향 관계 보다는 다대일 양방향 관계로 바꾸어서 전환
// 외래키가 여기에 존재하므로 외래키를 수정해야 하므로 SQL이 더 많이 실행되어 테이블이 많아지는 것 그리고 성능차원에서 해줘야 하는 부분이다.
    private String name;

    //양방향 매핑.
    @ManyToOne
    @JoinColumn(name="requestUserId")
    private Users requestUserId;

    //양방향 매핑
    @ManyToOne
    @JoinColumn(name="responseUserId")
    private Users responseUserId;

    @OneToMany(mappedBy = "chatRoomTable" ,fetch = FetchType.LAZY,cascade = CascadeType.REMOVE ,orphanRemoval = true)
    private List<ChatMessage> chatMessages;

    @Builder
    public ChatRoomTable(String RoomId, String name, Users requestUserId, Users responseUserId) {
        this.roomId = RoomId;
        this.requestUserId=requestUserId;
        this.responseUserId=responseUserId;
        this.name=name;
    }


}
