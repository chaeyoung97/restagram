package com.example.restagram.domain.posts;

import com.example.restagram.domain.users.Users;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostLikeVo {
    private Posts post;
    private boolean liked;
    
    @Builder
    public PostLikeVo(Posts posts, Users users){
        this.post = posts;
        this.liked = users.liked(posts);
    }
}
