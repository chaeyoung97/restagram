package com.example.restagram.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String content;

    @Builder
    public PostsUpdateRequestDto(String content){
        this.content = content;
    }
}
