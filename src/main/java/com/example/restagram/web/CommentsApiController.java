package com.example.restagram.web;

import com.example.restagram.domain.comments.CommentsRepository;
import com.example.restagram.service.CommentsService;
import com.example.restagram.web.dto.CommentsSaveRequestDto;
import com.example.restagram.web.dto.CommentsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/comments")
@RestController
public class CommentsApiController {
    private final CommentsRepository commentsRepository;
    private final CommentsService commentsService;

    @PostMapping("")
    public Long save(@PathVariable Long postId, @RequestBody CommentsSaveRequestDto requestDto){
        return commentsService.save(postId, requestDto);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody CommentsUpdateRequestDto requestDto){
        return commentsService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id){
        return commentsService.delete(id);
    }
}
