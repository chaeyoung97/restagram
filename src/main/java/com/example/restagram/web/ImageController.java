package com.example.restagram.web;

import java.io.IOException;
import java.util.List;

import com.example.restagram.config.LoginUser;
import com.example.restagram.domain.posts.PostsRepository;
import com.example.restagram.domain.users.SessionUser;
import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.users.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.restagram.service.ImageService;


@Controller
@RequestMapping("/image")
public class ImageController {
	
	@Autowired
	private ImageService service;
	
	@Autowired
	private UsersRepository usersRepository;
	
	//User 프로필 Control
	@GetMapping("/user/{id}")
	public String testProfile(@PathVariable Long id, Model model){
		service.getProfileImage(id, model);
		return "image/test";
	}

	@PostMapping("/user/{id}")
	public String postProfile(@PathVariable Long id ,@RequestParam MultipartFile file, RedirectAttributes attr, @LoginUser SessionUser user) {
		if(user == null)
			return "login";
	    Users sessionedUser = usersRepository.findByUsername(user.getUsername()).get();
		service.saveProfileImage(id, file, attr, sessionedUser);
		return "redirect:/profile/" + sessionedUser.getUsername();
	}
	
	@PutMapping("/user/{id}")
	public String updateProfile(@PathVariable Long id ,@RequestParam MultipartFile file, RedirectAttributes attr,  @LoginUser SessionUser user){
		if(user==null)
			return "login";
		Users sessionedUser = usersRepository.findByUsername(user.getUsername()).get();
		service.updateProfileImage(id, file, attr);
		return "redirect:/profile/" + sessionedUser.getUsername();
	}
	
	@DeleteMapping("/user/{id}")//프로필 삭제
	public String delteProfile(@PathVariable Long id , RedirectAttributes attr) {
		service.deleteProfileImage(id);
		return "redirect:/profile";
	}
	
	
	//게시물 Control
	@GetMapping("/post/{postId}")
	public String testPost(@PathVariable Long postId, Model model){
		service.getPostImages(postId, model);
		return "image/posttest";
	}
	
//	@PostMapping("/post/{postId}")
//	public String postImage(@PathVariable Long postId, @RequestParam List<MultipartFile> files, RedirectAttributes attr) throws IllegalStateException, IOException {
//		service.savePostImages(postId, files, attr);
//		return "redirect:/image/post/{postId}";
//	}
//
//	@PutMapping("/post/{postId}")
//	public String updatePostImage(@PathVariable Long postId,@RequestParam List<MultipartFile> files, RedirectAttributes attr) throws IllegalStateException, IOException {
//		service.updatePostImages(postId, files,attr);
//		return "redirect:/image/post/{postId}";
//	}
	
	@DeleteMapping("/post/{postId}")
	public String deletePostImage(@PathVariable Long postId, RedirectAttributes attr ) {
		service.deletePostImage(postId);
		return "redirect:/image/post/{postId}";
	}
}
