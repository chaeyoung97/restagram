package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.User;
import com.example.demo.dto.UserRepository;


@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
//	private List<User> users=new ArrayList<User>();
	
	//회원가입 페이지로 이동
	@GetMapping("/signUpForm")
	public String signUpForm() {
		System.out.print("hihi");
		return "/user/signUpForm";
	}
	
	//회원가입
	@PostMapping("")
	public String insta_user(User user) {
		System.out.println("user: "+user);
		userRepository.save(user);
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
	public String update(@PathVariable Long id, User newUser) {
		User user=userRepository.findById(id).get();
		user.update(newUser);
		return "redirect:/users";
		
	}
	//로그인 페이지로 이동
	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/login";
	}
	
	//로그인
	@PostMapping("/login")
	public String login(User user) {
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
