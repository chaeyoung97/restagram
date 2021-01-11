package com.example.restagram.web.postDto;

import com.example.restagram.domain.comments.Comments;
import com.example.restagram.domain.images.Images;
import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.tables.LikesTables;
import com.example.restagram.domain.tables.TagsTables;
import com.example.restagram.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class PostsResponseDto {
    private Long id;
    private Users user;
    private String content;
    private List<TagsTables> tags;
    private List<LikesTables> likes ;
    private List<Comments> comments ;
    private List<Images> images;
    private Long commentsCnt;
    private Long likeCnt;
    private boolean liked;

    @Builder
    public PostsResponseDto(Posts posts, Users users){
        this.id = posts.getId();
        this.user = posts.getUser();
        this.content = posts.getContent();
        this.tags = posts.getTags();
        this.likes = posts.getLikes();
        this.comments = posts.getComments();
        this.images = posts.getImages();
        this.commentsCnt = posts.getCommentsCnt();
        this.likeCnt = posts.getLikeCnt();
        this.liked = users.liked(posts.getId());
    }
}
