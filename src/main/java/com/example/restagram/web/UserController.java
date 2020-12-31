package com.example.restagram.web;
import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.users.UsersRepository;
import com.example.restagram.follow.FollowRepository;
import com.example.restagram.security.InstaUserDetails;
import com.example.restagram.service.UserService;
import com.example.restagram.utils.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;


@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private UserService service;

	@Autowired
	private FollowRepository followRepository;

	@Autowired
	private BCryptPasswordEncoder pwEncoder;
//	private List<User> users=new ArrayList<User>();

	//회원가입 페이지로 이동
	@GetMapping("/signUpForm")
	public String signUpForm() {
		return "/user/signUpForm";
	}

	//회원가입
	@PostMapping("")
	public String insta_user(Users users) {
		System.out.println("user: "+ users);
		String originPassword=users.getPassword();
		String encodPassword= pwEncoder.encode(originPassword);
		userRepository.save(users);
		return "redirect:/users";
	}

	//사용자 목록
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/userlist";
	}

	//회원 정보 수정 페이지
	@GetMapping("/{id}/signUpForm")
	public String userUpdate(@PathVariable Long id, Model model) {

		model.addAttribute("user", userRepository.findById(id).get());
		return "/user/userUpdate";
	}

	//회원 정보 수정
	@PostMapping("/{id}")
	public String update(@PathVariable Long id, Users newUsers) {
		Users users =userRepository.findById(id).get();
		users.update(newUsers);
		return "redirect:/users";

	}
	//로그인 페이지로 이동
	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/login";
	}

	//로그인
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		Users user=userRepository.findByUserId(userId);

		if(user==null) {
			return "redirect:/users/login";
		}
		if(!service.matchPassword(userId, password))
		return "redirect:/";
	}

	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
		return "redirect:/";
	}

	//프로필 업로드
	@PostMapping("/profileUpload")
	public String profileUpload(
			@RequestParam("profileImage")MultipartFile file,
			@AuthenticationPrincipal InstaUserDetails userDetails
			) throws IOException {
		//프로필 이미지 업로드 후 자신의 프로필 페이지로 이동
		Users principalUser=userDetails.getUser();

		Path filePath= Paths.get("");
		Files.write(filePath, file.getBytes());

		Optional<Users> optUser=userRepository.findById(principalUser.getId());

		Users user=optUser.get();
		user.setProfileImage(???);
		userRepository.save(user);

		return "redirect:/user/"+principalUser.getId();
	}

	//프로필 정보
	@GetMapping("/profile")
	public String profile(
			@AuthenticationPrincipal InstaUserDetails userDetails,
			@PathVariable Long id,
			Model model) {
		Optional<Users> optUser=userRepository.findById(id);
		Users user=optUser.get();

		//imageCount
		for(Image item:user.getProfileImage()) {

		}

		//followCount
		int followCount=followRepository.countByFollowId(user.getId());
		model.addAttribute("followCount", followCount);
		//followerCount
		int followerCount=followRepository.countbyFollowerId(user.getId());
		model.addAttribute("followerCount", followerCount);

		//follow 유무
		Users principalUser=userDetails.getUser();
		int followCheck=followRepository.countByFollowIdAndFollowerId(principalUser.getId(), id);
		model.addAttribute("followCheck", followCheck);

		return "/user/profile";
	}


}
