package com.example.restagram.domain.comments;

import com.example.restagram.domain.BaseTimeEntity;
import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.posts.Posts;
import lombok.Builder;
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
//
//    @ManyToOne
//    @JoinColumn(name ="user_id")
//    private Users user;

    @Builder
    public Comments(Posts posts,String content){
        this.post = posts;
        this.content = content;
    }

    public void update(String content){
        this.content = content;
    }
}
