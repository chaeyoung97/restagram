package com.example.restagram.service;

import com.example.restagram.config.LoginUser;
import com.example.restagram.domain.tables.FollowTable;
import com.example.restagram.domain.tables.FollowTableRepository;
import com.example.restagram.domain.users.SessionUser;
import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final FollowTableRepository followTableRepository;

    /*
        이미 팔로우가 되어 있다면 FollowTable에서 삭제 (언팔로우)
        팔로우가 되어 있지 않다면 FollowTable에 추가(팔로우)
     */
    @Transactional
    public Long follow(
            //누가(로그인한 사용자)
            Users fromUser,
            //누구를(팔로우한 사용자)
            Users toUser) {

        Optional<FollowTable> follow = followTableRepository.findByFromUserAndToUser(fromUser, toUser);

        //follow테이블에 이미 존재한다면 언팔로우
        if(follow.isPresent()) {
            followTableRepository.delete(follow.get());
            return 0L;
        }
        return followTableRepository.save(FollowTable.builder().fromUser(fromUser).toUser(toUser).build()).getId();
    }

    @Transactional
    public Long delete(Long id){
        followTableRepository.deleteById(id); // cascadeType.REMOVE옵션을 줬기 때문에 post만 삭제해도 태그, 댓글, 좋아요가 모두 삭제됨
        return id;
    }

}
