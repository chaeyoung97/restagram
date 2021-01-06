package com.example.restagram.web;

import com.example.restagram.config.LoginUser;
import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.posts.PostsRepository;
import com.example.restagram.domain.users.SessionUser;
import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.users.UsersRepository;
import com.example.restagram.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/like/user/{userId}/post/{postId}")
@RestController
public class likesApiController {

    private final UsersRepository usersRepository;
    private final LikesService likesService;
    private final PostsRepository postsRepository;

    /*
		이미 좋아요 되어 있다면 좋아요 취소
		아니라면 좋아요 등록하는 api

		return -4 :해당 게시물 없음
		       -3 :세션유저와 좋아요 누른 유저가 다름
		       -2 :해당 유저 없음
			   -1 :세션유저 없음
			    0 :좋아요 취소 성공
			    N :좋아요 성공

	 */
    @Transactional(readOnly = true)
    @GetMapping("")
    public Long like(@LoginUser SessionUser sessionUser, @PathVariable Long userId, @PathVariable Long postId){
        if(sessionUser == null)
            return -1L;
        Optional<Users> users = usersRepository.findByUsername(sessionUser.getUsername());
        if(!users.isPresent())
            return 2L;
        if(!userId.equals(users.get().getId()))
            return -3L;
        Optional<Posts> posts = postsRepository.findById(postId);
        if(!posts.isPresent())
            return -4L;
        return likesService.like(users.get(), posts.get());
    }
}
