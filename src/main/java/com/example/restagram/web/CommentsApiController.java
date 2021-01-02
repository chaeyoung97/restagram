package com.example.restagram.web;

import com.example.restagram.domain.comments.CommentsRepository;
import com.example.restagram.service.CommentsService;
import com.example.restagram.utils.HttpSessionUtils;
import com.example.restagram.web.dto.CommentsSaveRequestDto;
import com.example.restagram.web.dto.CommentsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/comments")
@RestController
public class CommentsApiController {
    private final CommentsRepository commentsRepository;
    private final CommentsService commentsService;

    @PostMapping("")
    public Long save(@PathVariable Long postId, @RequestBody CommentsSaveRequestDto requestDto, HttpSession session){
        return commentsService.save(postId, requestDto, HttpSessionUtils.getUserFromSession(session));
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
