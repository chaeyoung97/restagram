package com.example.restagram.service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.posts.PostsRepository;
import com.example.restagram.domain.users.SessionUser;
import com.example.restagram.domain.users.Users;
import com.example.restagram.utils.HttpSessionUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.restagram.domain.images.Images;
import com.example.restagram.domain.images.ImagesRepository;

@Service
public class ImageService {
	@Autowired
	private ImagesRepository imageRepo;
	@Autowired
	private PostsRepository postsRepository;

	@Transactional
	public void savePostImages(Long postId, List<MultipartFile> files, RedirectAttributes attr, Users user) {
		System.out.println("이미지 저장함수 실행");
		Posts posts = postsRepository.findById(postId).get();
		String path = System.getProperty("user.dir") + "\\bin\\main\\static\\images\\post\\"+ posts.getId().toString();
		File folder = new File(path);
		if(!folder.exists()) {
			folder.mkdirs();
		}

		for(MultipartFile file : files){
			System.out.println("이미지 저장");
			Long count = imageRepo.count();
			String fName = Long.toString(count+1) + ".jpg";
			String fURL = path + "\\" + fName;
			saveImage(fURL, file);
			Images image = Images.builder()
					.imageName(fName)
					.imageURL("/images/post/"+posts.getId().toString()+"/"+fName)
					.post(posts)
					.user(user)
					.build();
			imageRepo.save(image);
		}
		attr.addFlashAttribute("images", imageRepo.findAll());
		System.out.println("정상종료");
	}

	@Transactional(readOnly = true)
	public void getPostImages(Long postId, Model model) {
		model.addAttribute("postId", postId);
		List<Images> imageList =  imageRepo.findAllByPostId(postId);
		model.addAttribute("images", imageList);
		System.out.println(imageList.size());
	}

	@Transactional
	public void updatePostImages(Long postId, List<MultipartFile> files, RedirectAttributes attr) {
		Posts posts = postsRepository.findById(postId).get();
		Users user = posts.getUser();
		List<Images> imageList = imageRepo.findAllByPostId(posts.getId());
		for(Images image : imageList) {
			String prevURL = System.getProperty("user.dir") + "/bin/main/static"+  image.getImageURL();
			deleteImage(prevURL, image);
			imageRepo.delete(image);
		}		
		String path = System.getProperty("user.dir") + "\\bin\\main\\static\\images\\post\\"+ posts.getId().toString();
		for(MultipartFile file : files) {
			Long count = imageRepo.count();
			String fName = Long.toString(count+1) + ".jpg";
			String fURL = path + "\\" + fName;
			saveImage(fURL, file);
			Images image = Images.builder()
					.imageName(fName)
					.imageURL("/images/post/"+posts.getId().toString()+"/"+fName)
					.post(posts)
					.user(user)
					.build();
			imageRepo.save(image);
		}
		
		attr.addFlashAttribute("images", imageRepo.findAll());
	}

	@Transactional
	public void deletePostImage(Long postId) {
		List<Images> imageList = imageRepo.findAllByPostId(postId);
		for(Images image : imageList) {
			String prevURL = System.getProperty("user.dir") + "/bin/main/static"+  image.getImageURL();
			deleteImage(prevURL, image);
			imageRepo.delete(image);
		}		
	}

	@Transactional
	public void saveProfileImage(Long id, MultipartFile file, RedirectAttributes attr, Users user){
		String path = System.getProperty("user.dir") + "\\bin\\main\\static\\images\\profile\\"+ id.toString();
		File folder = new File(path);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		String fName = "profile.jpg";
		String fURL = path + "\\" + fName;
		saveImage(fURL, file);
		Images image = Images.builder().imageName(fName)
					.imageURL("/images/profile/"+id.toString()+"/"+fName)
					.post(null)
					.user(user)
					.build();
		attr.addFlashAttribute("ImageName", fName);
		attr.addFlashAttribute("ImageURL","/images/profile/"+id.toString()+"/"+fName);
		imageRepo.save(image);
	}

	@Transactional
	public void updateProfileImage(Long id, MultipartFile file, RedirectAttributes attr) {
		Images image = imageRepo.findByUserIdAndImageName(id, "profile.jpg");
		deleteImage(System.getProperty("user.dir") + "/bin/main/static"+  image.getImageURL(), image);		
		
		String path = System.getProperty("user.dir") + "\\bin\\main\\static\\images\\profile\\"+ id.toString();
		File folder = new File(path);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		String fName = "profile.jpg";
		String fURL = path + "\\" + fName;
		saveImage(fURL, file);
		
		attr.addFlashAttribute("ImageName", fName);
		attr.addFlashAttribute("ImageURL","/images/profile/"+id.toString()+"/"+fName);
	}

	@Transactional(readOnly = true)
	public void getProfileImage(Long id, Model model) {
		model.addAttribute("id", id);
		if(imageRepo.existsByUserId(id)) {
			Images profileImage =  imageRepo.findByUserIdAndImageName(id, "profile.jpg");
			model.addAttribute("ImageName", profileImage.getImageName());
			model.addAttribute("ImageURL", profileImage.getImageURL());			
		}
	}

	@Transactional
	public void deleteProfileImage(Long id) {
		Images image =  imageRepo.findByUserIdAndImageName(id, "profile.jpg");
		String prevURL = System.getProperty("user.dir") + "/bin/main/static"+  image.getImageURL();
		deleteImage(prevURL,image);
		imageRepo.delete(image);
	}
	
	private void deleteImage(String prevURL, Images image) {
		File prevFile = new File(prevURL);
		if(prevFile.exists()) {
			if(prevFile.delete()) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}
		}else {
			System.out.println("파일이 존재하지 않습니다.");
		}
	}

	private void saveImage(String fURL, MultipartFile file){
		File destFile = new File(fURL);
		BufferedImage bImage;
		try {
			bImage = ImageIO.read(file.getInputStream());		
			BufferedImage result = new BufferedImage(bImage.getWidth(), bImage.getHeight(), BufferedImage.TYPE_INT_RGB);
			result.createGraphics().drawImage(bImage, 0, 0, Color.WHITE,null);
			ImageIO.write(result, "jpg", destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
