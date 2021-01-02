package com.example.restagram.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String content;
    private MultipartFile[] multipartFiles;


    @Builder
    public PostsUpdateRequestDto(String content, MultipartFile[] multipartFiles){
        this.content = content;
        this.multipartFiles = multipartFiles;
    }
}
