package com.example.restagram.web.dto;

import com.example.restagram.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostsSaveRequestDto {
    private String content;

    @Builder
    public PostsSaveRequestDto(String content){
        this.content = content;
    }

    public Posts toEntity(){
        return Posts.builder().content(content).build();
    }
}

