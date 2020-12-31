package com.example.restagram.web.dto;

import com.example.restagram.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
public class PostsSaveRequestDto {
    private String content;
    private MultipartFile[] multipartFiles;
    @Builder
    public PostsSaveRequestDto(String content, MultipartFile[] multipartFiles){
        this.content = content;
        this.multipartFiles = multipartFiles;
    }

    public Posts toEntity(){
        return Posts.builder().content(content).build();
    }
}

