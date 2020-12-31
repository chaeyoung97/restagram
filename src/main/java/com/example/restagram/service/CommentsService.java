package com.example.restagram.service;

import com.example.restagram.domain.comments.Comments;
import com.example.restagram.domain.comments.CommentsRepository;
import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.posts.PostsRepository;
import com.example.restagram.web.dto.CommentsSaveRequestDto;
import com.example.restagram.web.dto.CommentsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(Long postId, CommentsSaveRequestDto requestDto){
        Posts posts = postsRepository.findById(postId).get();
        Comments comments = commentsRepository.save(requestDto.toEntity(posts));
        posts.addComments();
        return postId;
    }

    @Transactional
    public Long update(Long id, CommentsUpdateRequestDto requestDto){
       Comments comments = commentsRepository.findById(id).get();
       comments.update(requestDto.getContent());
       return id;

    }

    @Transactional
    public Long delete(Long postId, Long id){
        Posts posts = postsRepository.findById(postId).get();
        posts.removeComments();
        commentsRepository.deleteById(id);
        return id;
    }
}