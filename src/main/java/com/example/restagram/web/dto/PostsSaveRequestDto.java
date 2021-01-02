package com.example.restagram.web.dto;

import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
public class PostsSaveRequestDto {
    private String content;
    private Users user;
    private MultipartFile[] multipartFiles;

    @Builder
    public PostsSaveRequestDto(Users user, String content, MultipartFile[] multipartFiles){
        this.user = user;
        this.content = content;
        this.multipartFiles = multipartFiles;
    }

    public Posts toEntity(){
        return Posts.builder().user(user).content(content).build();
    }
}

