package com.example.restagram.domain.Users;

import com.example.restagram.domain.BaseTimeEntity;
import com.example.restagram.domain.comments.Comments;
import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.tables.LikesTables;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String userName;

    @Builder
    public User(String userName){
        this.userName = userName;
    }
}
