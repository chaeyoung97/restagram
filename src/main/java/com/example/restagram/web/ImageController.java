package com.example.restagram.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.restagram.domain.images.Images;
import com.example.restagram.domain.images.ImagesRepository;
import com.example.restagram.service.ImageService;

@Controller
@RequestMapping("/image")
public class ImageController {
	
	@Autowired
	private ImagesRepository imageRepo;
	
	@Autowired
	private ImageService service;
	
	@GetMapping("/user/{id}")
	public String testProfile(@PathVariable Long id, Model model){
		model.addAttribute("id", id);
		return "image/test";
	}

	@PostMapping("/user/{id}")
	public String postProfile(@PathVariable Long id ,@RequestParam MultipartFile file, RedirectAttributes attr) throws IllegalStateException, IOException {
		//String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\"+ fName;
		//policy
		//filenameutils사용
		
		String path = System.getProperty("user.dir") + "\\bin\\main\\static\\images\\profile\\"+ id.toString();
		File folder = new File(path);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		String fName = file.getOriginalFilename();
		String fURL = path + "\\" + fName;
		
		File destFile = new File(fURL);
		file.transferTo(destFile);
		Images image = new Images();
		image.setImageName(fName);
		image.setImageURL("/images/profile/"+id.toString()+"/"+fName);
		
		imageRepo.save(image);
		attr.addFlashAttribute("ImageName", fName);
		attr.addFlashAttribute("ImageURL","/images/profile/"+id.toString()+"/"+fName);
		
		//사용자 프로필 페이지로 리다이렉트
		return "redirect:/image/user/{id}";
	}
	
	@PutMapping("/user/{id}")
	public String updateProfile(@PathVariable Long id ,@RequestParam MultipartFile file, RedirectAttributes attr) throws IllegalStateException, IOException {
		
		//filenameutils사용
		
		String path = System.getProperty("user.dir") + "\\bin\\main\\static\\images\\profile\\"+ id.toString();
		File folder = new File(path);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		String fName = file.getOriginalFilename();//filenameutils사용해서 이름 변경
		String fURL = path + "\\" + fName;
		
		File destFile = new File(fURL);
		file.transferTo(destFile);
		Images image = new Images();
		image.setImageName(fName);
		image.setImageURL("/images/profile/"+id.toString()+"/"+fName);
		
		imageRepo.save(image);
		attr.addFlashAttribute("ImageName", fName);
		attr.addFlashAttribute("ImageURL","/images/profile/"+id.toString()+"/"+fName);
		
		//사용자 프로필 페이지로 리다이렉트
		return "redirect:/image/user/{id}";
	}
	
	@GetMapping("/post/{postId}")
	public String testPost(@PathVariable Long postId, Model model){
		model.addAttribute("postId", postId);
		return "image/posttest";
	}
	
	@PostMapping("/post/{postId}")
	public String postImage(@PathVariable Long postId,@RequestParam MultipartFile[] files, RedirectAttributes attr) throws IllegalStateException, IOException {
		System.out.println("before service");		
		service.savePostImages(postId, files);
		
		attr.addFlashAttribute("images", imageRepo.findAll());
		
		//테스트를 위해 String으로 사용, 실제 코드는 컨트롤러에서 게시물 등록에 의한 post 맵핑 ->
		return "redirect:/image/post/{postId}";
	}
}
