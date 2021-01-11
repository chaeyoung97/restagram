
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
@RequestMapping("/follow")
@RestController
public class FollowApiController {

	private final UsersRepository usersRepository;
	private final FollowTableRepository followTableRepository;
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

//	//사용자가 팔로우하는 사람의 팔로워 목록
//	@GetMapping("/follow/follower/{id}")
//	public String followFollower(
//			@AuthenticationPrincipal PrincipalDetail userDetails,
//			@PathVariable Long id,
//			Model model) {
//		List<Follow> followers = followRepository.findByToUser(id);
//		List<Follow> principalfollows = followRepository.findByToUser(userDetails.getUser().getId());
//
//		//follower 목록에 있는 id와 principalfollow 목록에 있는 id를 비교
//		for (Follow follow1 : followers) {
//			for (Follow follow2 : principalfollows) {
//				if (follow1.getFromUser().getId() == follow2.getFromUser().getId()) {
//					follow1.setFollowState(true);
//				}
//			}
//		}
//		model.addAttribute("followers", followers);
//		return "/follow/follower";
//	}
//
//	//사용자가 팔로우하는 사람을 팔로우하는 목록
//	@GetMapping("/follow/follow/{id}")
//	public String followFollow(
//			@AuthenticationPrincipal PrincipalDetail userDetails,
//			@PathVariable Long id,
//			Model model) {
//		List<Follow> follows=followRepository.findByFromUser(id);
//		List<Follow> principalfollows=followRepository.findByFromUser(userDetails.getUser().getId());
//
//		//follow 목록에 있는 id와 principalfollow 목록에 있는 id를 비교
//		for(Follow follow1:follows) {
//			for(Follow follow2:principalfollows) {
//				if(follow1.getFromUser().getId()==follow2.getFromUser().getId()) {
//					follow1.setFollowState(true);
//				}
//			}
//		}
//		model.addAttribute("follows", follows);
//		return "/follow/follow";
//	}
}

