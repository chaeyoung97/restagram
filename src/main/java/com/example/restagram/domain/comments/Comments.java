package com.example.restagram.domain.comments;

import com.example.restagram.domain.BaseTimeEntity;
import com.example.restagram.domain.Users.User;
import com.example.restagram.domain.posts.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comments extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(length = 512)
    private String content;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Posts post;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;
}
