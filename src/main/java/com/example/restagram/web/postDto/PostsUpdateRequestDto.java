package com.example.restagram.web.postDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String content;
    private List<MultipartFile> files;


    @Builder
    public PostsUpdateRequestDto(String content, List<MultipartFile> files){
        this.content = content;
        this.files = files;
    }
}
