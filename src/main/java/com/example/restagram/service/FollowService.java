package com.example.restagram.service;

import com.example.restagram.config.LoginUser;
import com.example.restagram.domain.tables.FollowTable;
import com.example.restagram.domain.tables.FollowTableRepository;
import com.example.restagram.domain.users.SessionUser;
import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final UsersRepository usersRepository;
    private final FollowTableRepository followTableRepository;

    /*
        이미 팔로우가 되어 있다면 FollowTable에서 삭제 (언팔로우)
        팔로우가 되어 있지 않다면 FollowTable에 추가(팔로우)
     */
    public Long follow(
            //누가(로그인한 사용자)
            Users fromUser,
            //누구를(팔로우한 사용자)
            Long toUserId) {
        Users toUser =  usersRepository.findById(toUserId).get();
        Optional<FollowTable> follow = followTableRepository.findByFromUserAndToUser(fromUser, toUser);

        //follow테이블에 이미 존재한다면
        if(follow.isPresent()) {
            followTableRepository.delete(follow.get());
            return 0L;
        }
        return followTableRepository.save(FollowTable.builder().fromUser(fromUser).toUser(toUser).build()).getId();
    }

}
