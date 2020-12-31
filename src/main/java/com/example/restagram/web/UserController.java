package com.example.restagram.web;
import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UsersRepository userRepository;

//	private List<User> users=new ArrayList<User>();

	//회원가입 페이지로 이동
	@GetMapping("/signUpForm")
	public String signUpForm() {
		return "user/signUpForm";
	}

	//회원가입
	@PostMapping("")
	public String insta_user(Users users) {
		System.out.println("user: "+ users);
		userRepository.save(users);
		return "redirect:/users";
	}

	//사용자 목록
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/userlist";
	}

	//회원 정보 수정 페이
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
	public String login(Users users) {
		return "redirect:/";
	}

	//로그아웃
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/";
	}

	//팔로우
	@GetMapping("/follow")
	public String follow() {
		return "";
	}

	//팔로워
	@GetMapping("/follower")
	public String follower() {
		return "";
	}
}
