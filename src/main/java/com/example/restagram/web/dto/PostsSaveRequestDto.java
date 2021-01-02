package com.example.restagram.web.dto;

import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.users.Users;
import com.example.restagram.utils.HttpSessionUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostsSaveRequestDto {
    private String content;
    private List<MultipartFile> files;

    @Builder
    public PostsSaveRequestDto(String content, List<MultipartFile> files){
        this.content = content;
        this.files = files;
    }

    public Posts toEntity(Users users){
        return Posts.builder().user(users).content(content).build();
    }
}

