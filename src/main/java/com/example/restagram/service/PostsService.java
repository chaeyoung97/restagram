package com.example.restagram.service;

import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.posts.PostsRepository;
import com.example.restagram.domain.tables.TagsTables;
import com.example.restagram.domain.tables.TagsTablesRepository;
import com.example.restagram.domain.tags.Tags;
import com.example.restagram.domain.tags.TagsRepository;
import com.example.restagram.domain.users.Users;
import com.example.restagram.utils.ExtractHashTag;
import com.example.restagram.utils.HttpSessionUtils;
import com.example.restagram.web.dto.PostsSaveRequestDto;
import com.example.restagram.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final TagsRepository tagsRepository;
    private final TagsTablesRepository tagsTablesRepository;
    private final ImageService imageService;

    @Transactional
    public Posts save(PostsSaveRequestDto requestDto, Users users){
        Posts posts = postsRepository.save(requestDto.toEntity(users));
        List<String>tags = ExtractHashTag.extract(requestDto.getContent());
        Optional<Tags> tag;

        if(!tags.isEmpty()){     //추출된 태그가 있다면
            for(int i=0; i <tags.size(); i++){
                tag = tagsRepository.findByTagName(tags.get(i));
                if(tag.isPresent()){    // Tags에 이미 태그가 등록되어 있다면
                    tagsTablesRepository.save(TagsTables.builder().posts(posts).tags(tag.get()).build()); //태그 테이블에 바로 등록
                }
                else{   //Tags에 태그가 등록되어 있지 않다면
                    tag = Optional.of(tagsRepository.save(Tags.builder().tagName(tags.get(i)).build()));    //태그를 새로 등록하고
                    tagsTablesRepository.save(TagsTables.builder().posts(posts).tags(tag.get()).build());   //태그 테이블에 등록
                    System.out.println(">>> 새로운 태그 등록 :" + tag.get().getTagName());

                }
            }
        }
        return posts;
    }

    //더티체킹은 트랜젝션안에서만 이루어져서 @Transactional어노테이션이 업으면 더티체킹이 안됨!!!!
    @Transactional
    public Posts update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).get();
        posts.update(requestDto.getContent());
        for(int i=0; i<posts.getTags().size(); i++){
            tagsTablesRepository.delete(posts.getTags().get(i));    //해당 게시물에 등록된 태그를 제거(태그 테이블에서 제거)(이케하면 posts의 tag리스트에서도 삭제됨을 확인함)
        }

        List<String>tags = ExtractHashTag.extract(requestDto.getContent());
        Optional<Tags> tag;

        if(!tags.isEmpty()){     //추출된 태그가 있다면
            for(int i=0; i <tags.size(); i++){
                tag = tagsRepository.findByTagName(tags.get(i));
                if(tag.isPresent()){    // Tags에 이미 태그가 등록되어 있다면

                    tagsTablesRepository.save(TagsTables.builder().posts(posts).tags(tag.get()).build()); //태그 테이블에 바로 등록
                }
                else{   //Tags에 태그가 등록되어 있지 않다면
                    tag = Optional.of(tagsRepository.save(Tags.builder().tagName(tags.get(i)).build()));    //태그를 새로 등록하고
                    tagsTablesRepository.save(TagsTables.builder().posts(posts).tags(tag.get()).build());   //태그 테이블에 등록
                    System.out.println(">>> 새로운 태그 등록 :" + tag.get().getTagName());

                }
            }
        }
        return posts;
    }

    @Transactional
    public Long delete(Long id){
        postsRepository.deleteById(id); // cascadeType.REMOVE옵션을 줬기 때문에 post만 삭제해도 태그, 댓글, 좋아요가 모두 삭제됨
        return id;
    }

    @Transactional(readOnly = true)
    public Posts findByid(Long id){
        return postsRepository.findById(id).get();
    }

//    @Transactional(readOnly = true)
//    public Long findByid(Long id){
//        return new Long(postsRepository.findById(id).get().getTags().size());
//    }
}
