package com.example.restagram.web.commentDto;


import com.example.restagram.domain.comments.Comments;
import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class CommentsResponseDto {
    private Long id;
    private String content;
    private String username;
    private String profileImage;
    private Long postId;
    private String createdDate;
    private String modifiedDate;

    @Builder
    public CommentsResponseDto(Comments comments){
        this.id = comments.getId();
        this.content =comments.getContent();
        this.username = comments.getUser().getUsername();
        this.postId = comments.getPost().getId();
        this.createdDate = comments.getFormattedCreatedDate();
        this.modifiedDate = comments.getFormattedModifiedDate();
        this.profileImage = comments.getUser().getProfileImage();
    }
}
