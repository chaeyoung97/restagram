package com.example.restagram.domain.tables;

import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.posts.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class LikesTables{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts post;
}
