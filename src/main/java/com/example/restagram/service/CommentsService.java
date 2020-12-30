package com.example.restagram.service;

import com.example.restagram.domain.comments.Comments;
import com.example.restagram.domain.comments.CommentsRepository;
import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.posts.PostsRepository;
import com.example.restagram.domain.users.Users;
import com.example.restagram.web.dto.CommentsSaveRequestDto;
import com.example.restagram.web.dto.CommentsUpdateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(Long id, CommentsSaveRequestDto requestDto){
        Posts posts = postsRepository.findById(id).get();
        Comments comments = commentsRepository.save(requestDto.toEntity(posts));
        return id;
    }

    @Transactional
    public Long update(Long id, CommentsUpdateRequestDto requestDto){
       Comments comments = commentsRepository.findById(id).get();
       comments.update(requestDto.getContent());
       return id;

    }

    @Transactional
    public Long delete(Long id){
        commentsRepository.deleteById(id);
        return id;
    }
}
