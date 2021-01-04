
package com.example.restagram.web;

import com.example.restagram.config.PrincipalDetail;
import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.users.UsersRepository;
import com.example.restagram.follow.Follow;
import com.example.restagram.follow.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class FollowController {
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private FollowRepository followRepository;
	
	@PostMapping("/follow/{id}")
	public String follow(
            //누가(로그인한 사용자)
            @AuthenticationPrincipal PrincipalDetail userDetails,
            //누구를(팔로우한 사용자)
            @PathVariable Long id) {

		Users fromUser=userDetails.getUser();
		Optional<Users> optToUser=usersRepository.findById(id);
		Users toUser=optToUser.get();

		Follow follow=new Follow();
		follow.setFromUser(fromUser);
		follow.setToUser(toUser);
		followRepository.save(follow);

		return "ok";
	}

	@DeleteMapping("/follow/{id}")
	public @ResponseBody String unfollow(
			@AuthenticationPrincipal PrincipalDetail userDetails,
			@PathVariable Long id) {
		Users fromUser=userDetails.getUser();
		Optional<Users> optToUser=usersRepository.findById(id);
		Users toUser=optToUser.get();

		followRepository.deleteByFromUserIdAndToUserId(fromUser.getId(), toUser.getId());

		List<Follow> follows=followRepository.findAll();

		return "ok";
	}

	//사용자가 팔로우하는 사람의 팔로워 목록
	@GetMapping("/follow/follower/{id}")
	public String followFollower(
			@AuthenticationPrincipal PrincipalDetail userDetails,
			@PathVariable Long id,
			Model model) {
		List<Follow> followers = followRepository.findByToUser(id);
		List<Follow> principalfollows = followRepository.findByToUser(userDetails.getUser().getId());

		//follower 목록에 있는 id와 principalfollow 목록에 있는 id를 비교
		for (Follow follow1 : followers) {
			for (Follow follow2 : principalfollows) {
				if (follow1.getFromUser().getId() == follow2.getFromUser().getId()) {
					follow1.setFollowState(true);
				}
			}
		}
		model.addAttribute("followers", followers);
		return "/follow/follower";
	}

	//사용자가 팔로우하는 사람을 팔로우하는 목록
	@GetMapping("/follow/follow/{id}")
	public String followFollow(
			@AuthenticationPrincipal PrincipalDetail userDetails,
			@PathVariable Long id,
			Model model) {
		List<Follow> follows=followRepository.findByFromUser(id);
		List<Follow> principalfollows=followRepository.findByFromUser(userDetails.getUser().getId());

		//follow 목록에 있는 id와 principalfollow 목록에 있는 id를 비교
		for(Follow follow1:follows) {
			for(Follow follow2:principalfollows) {
				if(follow1.getFromUser().getId()==follow2.getFromUser().getId()) {
					follow1.setFollowState(true);
				}
			}
		}
		model.addAttribute("follows", follows);
		return "/follow/follow";
	}
}

