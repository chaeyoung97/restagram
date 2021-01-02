package com.example.restagram.web;

import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.users.UsersRepository;
import com.example.restagram.service.ImageService;
import com.example.restagram.service.PostsService;
import com.example.restagram.utils.HttpSessionUtils;
import com.example.restagram.web.dto.PostsSaveRequestDto;
import com.example.restagram.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.h2.engine.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostsApiController {
    private final PostsService postsService;
    private final ImageService imageService;
    private final UsersRepository usersRepository;

    @PostMapping("")
    public Long save(MultipartHttpServletRequest request, RedirectAttributes attributes, HttpSession session){

        Users users =  usersRepository.save(new Users("테스트사용자"));
        session.setAttribute("sessionedUser", users);
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .content(request.getParameter("content"))
                .files(request.getFiles("files,"))
                .build();
        Posts posts = postsService.save(requestDto, HttpSessionUtils.getUserFromSession(session));
        try{
            imageService.savePostImages(posts.getId(), requestDto.getFiles(), attributes);
        }catch (Exception e){
            System.out.println("이미지 저장 에러");
        }
        return posts.getId();
    }

//    @PostMapping("")
//    public Long save(@RequestBody PostsSaveRequestDto requestDto, RedirectAttributes attributes, HttpSession session){
//
//        Posts posts = postsService.save(requestDto, HttpSessionUtils.getUserFromSession(session));
//        try{
//            imageService.savePostImages(posts.getId(), requestDto.getFiles(), attributes);
//        }catch (Exception e){
//            System.out.println("이미지 저장 에러");
//        }
//        return posts.getId();
//    }

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
