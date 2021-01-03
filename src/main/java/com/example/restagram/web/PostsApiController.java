package com.example.restagram.web;

import com.example.restagram.config.LoginUser;
import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.users.SessionUser;
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
    public Long save(MultipartHttpServletRequest request, RedirectAttributes attributes, @LoginUser SessionUser user){
        if(user == null)
            return null;
        Users sessionedUser = usersRepository.findByUsername(user.getUsername()).get();
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .content(request.getParameter("content"))
                .files(request.getFiles("files"))
                .build();
        Posts posts = postsService.save(requestDto, sessionedUser);
        imageService.savePostImages(posts.getId(),  requestDto.getFiles(), attributes);

        return posts.getId();
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, MultipartHttpServletRequest request, RedirectAttributes attributes){
        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .content(request.getParameter("content"))
                .files(request.getFiles("files"))
                .build();
        Posts posts = postsService.update(id, requestDto);
        imageService.updatePostImages(id, requestDto.getFiles(), attributes);

        return posts.getId();
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id){
        imageService.deletePostImage(id);   //이미지 폴더 내에서도 삭제해주어야함
        return postsService.delete(id); //cascade 옵션때문에 태그테이블, 좋아요테이블, 이미지 모두 삭제됨
    }

    @GetMapping("/{id}")
    public Posts findById(@PathVariable Long id){ return postsService.findByid(id); }

//    @GetMapping("/{id}")
//    public Long findById(@PathVariable Long id){ return postsService.findByid(id); }
//

}
