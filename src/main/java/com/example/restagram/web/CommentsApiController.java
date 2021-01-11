package com.example.restagram.web;

import com.example.restagram.config.LoginUser;
import com.example.restagram.domain.comments.CommentsRepository;
import com.example.restagram.domain.users.SessionUser;
import com.example.restagram.domain.users.UsersRepository;
import com.example.restagram.service.CommentsService;
import com.example.restagram.web.commentDto.CommentsSaveRequestDto;
import com.example.restagram.web.commentDto.CommentsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/comments")
@RestController
public class CommentsApiController {
    private final CommentsRepository commentsRepository;
    private final CommentsService commentsService;
    private final UsersRepository usersRepository;

    @PostMapping("")
    public Long save(@PathVariable Long postId, @RequestBody CommentsSaveRequestDto requestDto, @LoginUser SessionUser sessionUser){
       if(sessionUser == null)
           return -1L;
        return commentsService.save(postId, requestDto, usersRepository.findByUsername(sessionUser.getUsername()).get());
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody CommentsUpdateRequestDto requestDto){
        return commentsService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long postId, @PathVariable Long id){
        return commentsService.delete(postId,id);
    }
}
