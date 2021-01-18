package com.example.restagram.domain.comments;

import com.example.restagram.domain.BaseTimeEntity;
import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.posts.Posts;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comments extends BaseTimeEntity {

    @JsonProperty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JsonProperty
    @Column(length = 512)
    private String content;

    @JsonProperty
    @ManyToOne
    @JoinColumn(name="post_id")
    private Posts post;

    @JsonProperty
    @ManyToOne
    @JoinColumn(name ="user_id")
    private Users user;

    @Builder
    public Comments(Posts posts, String content, Users user){
        this.post = posts;
        this.content = content;
        this.user = user;
    }

    public void update(String content){
        this.content = content;
    }
}
