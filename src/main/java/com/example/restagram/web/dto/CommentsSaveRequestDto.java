package com.example.restagram.web.dto;

import com.example.restagram.domain.comments.Comments;
import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentsSaveRequestDto {
    private String content;
    @Builder
    public CommentsSaveRequestDto(String content){
        this.content = content;
    }

    public Comments toEntity(Posts posts){
        return Comments.builder().posts(posts).content(content).build();
    }
}
