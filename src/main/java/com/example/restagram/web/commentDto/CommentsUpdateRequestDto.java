package com.example.restagram.web.commentDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentsUpdateRequestDto {
    private String content;

    @Builder
    public CommentsUpdateRequestDto(String content){
        this.content = content;
    }
}
