package com.example.restagram.service;

import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.posts.PostsRepository;
import com.example.restagram.domain.tables.LikesTables;
import com.example.restagram.domain.tables.LikesTablesRepository;
import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikesService {
    private final LikesTablesRepository likesTablesRepository;
    private final UsersRepository usersRepository;
    private final PostsRepository postsRepository;

    @Transactional
    public Long like(Users users, Posts posts){
        Optional<LikesTables> likesTables = likesTablesRepository.findByUserAndPost(users, posts);

        //Like테이블에 이미 존재 한다면 좋아요 취소
        if(likesTables.isPresent()){
            likesTablesRepository.delete(likesTables.get());
            posts.removeLikes();
            return 0L;
        }
        posts.addLikes();
        return likesTablesRepository.save(LikesTables.builder().users(users).posts(posts).build()).getId();
    }
}
