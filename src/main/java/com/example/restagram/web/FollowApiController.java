
package com.example.restagram.web;

import com.example.restagram.config.LoginUser;
import com.example.restagram.domain.users.SessionUser;
import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.users.UsersRepository;
import com.example.restagram.domain.tables.FollowTable;
import com.example.restagram.domain.tables.FollowTableRepository;
import com.example.restagram.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/follows")
@RestController
public class FollowApiController {

	private final UsersRepository usersRepository;
	private final FollowService followService;

	/*
		이미 팔로우가 되어 있다면 언팔로우
		팔로우가 되어 있지 않다면 팔로우하는 api

		return -3 :toUser와 fromUser가 같음
			   -2 :toUser 없음
			   -1 :세션유저 없음
			    0 :언팔 성공
			    N :팔로우 성공
	 */
	@GetMapping("/toUser/{toUserId}")
	public Long follow(
			//누가(로그인한 사용자)
			@LoginUser SessionUser sessionUser,
			//누구를(팔로우한 사용자)
			@PathVariable Long toUserId) {
		if(sessionUser == null)
			return -1L;
		Users fromUser = usersRepository.findByUsername(sessionUser.getUsername()).get();
		Optional<Users> toUser = usersRepository.findById(toUserId);
		if(!toUser.isPresent())
			return -2L;
		if(toUser.get().equals(fromUser))
			return -3L;


		return 	followService.follow(fromUser, toUser.get());
	}
}

