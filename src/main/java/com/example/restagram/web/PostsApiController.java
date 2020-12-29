package com.example.restagram.web;

import com.example.restagram.domain.posts.Posts;
import com.example.restagram.service.PostsService;
import com.example.restagram.web.dto.PostsSaveRequestDto;
import com.example.restagram.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){ return postsService.update(id, requestDto);}

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id){ return postsService.delete(id); }

//    @GetMapping("/{id}")
//    public Posts findById(@PathVariable Long id){ return postsService.findByid(id); }

    @GetMapping("/{id}")
    public Long findById(@PathVariable Long id){ return postsService.findByid(id); }


}
