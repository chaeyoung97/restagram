package com.example.restagram.web;

import com.example.restagram.domain.posts.Posts;
import com.example.restagram.service.ImageService;
import com.example.restagram.service.PostsService;
import com.example.restagram.web.dto.PostsSaveRequestDto;
import com.example.restagram.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostsApiController {
    private final PostsService postsService;
    private final ImageService imageService;

    @PostMapping("")
    public Long save(@RequestBody PostsSaveRequestDto requestDto, RedirectAttributes attributes){
        Posts posts = postsService.save(requestDto);
        try{
            imageService.savePostImages(posts.getId(), requestDto.getMultipartFiles(), attributes);
        }catch (Exception e){
            System.out.println("이미지 저장 에러");
        }
        return posts.getId();
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto, RedirectAttributes attributes){
        Posts posts = postsService.update(id, requestDto);
        try {
            imageService.updatePostImages(id, requestDto.getMultipartFiles(), attributes);
        }catch (Exception e){
            System.out.println("이미지 업데이트 에러");
        }
        return posts.getId();
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id){
        return postsService.delete(id); //cascade 옵션때문에 태그테이블, 좋아요테이블, 이미지 모두 삭제됨
    }

    @GetMapping("/{id}")
    public Posts findById(@PathVariable Long id){ return postsService.findByid(id); }

//    @GetMapping("/{id}")
//    public Long findById(@PathVariable Long id){ return postsService.findByid(id); }
//

}
