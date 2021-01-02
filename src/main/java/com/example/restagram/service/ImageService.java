package com.example.restagram.service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.posts.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	// 추후 infra 구축. -작성자:김병연
	public void savePostImages(Long postId, List<MultipartFile> files, RedirectAttributes attr) {
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
					.posts(posts)
					.userId(null)
					.build();
			imageRepo.save(image);
		}
		attr.addFlashAttribute("images", imageRepo.findAll());
		System.out.println("정상종료");
	}
	
	public void getPostImages(Long postId, Model model) {
		model.addAttribute("postId", postId);
		List<Images> imageList =  imageRepo.findAllByPostId(postId);
		model.addAttribute("images", imageList);
		System.out.println(imageList.size());
	}
	
	public void updatePostImages(Long postId, List<MultipartFile> files, RedirectAttributes attr) {
		Posts posts = postsRepository.findById(postId).get();
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
					.posts(posts)
					.userId(null)
					.build();
			imageRepo.save(image);
		}
		
		attr.addFlashAttribute("images", imageRepo.findAll());
	}
	
	public void deletePostImage(Long postId) {
		List<Images> imageList = imageRepo.findAllByPostId(postId);
		for(Images image : imageList) {
			String prevURL = System.getProperty("user.dir") + "/bin/main/static"+  image.getImageURL();
			deleteImage(prevURL, image);
			imageRepo.delete(image);
		}		
	}
	
	public void saveProfileImage(Long id, MultipartFile file, RedirectAttributes attr){
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
					.posts(null)
					.userId(id)
					.build();
		attr.addFlashAttribute("ImageName", fName);
		attr.addFlashAttribute("ImageURL","/images/profile/"+id.toString()+"/"+fName);
		imageRepo.save(image);
	}
	
	public void updateProfileImage(Long id, MultipartFile file, RedirectAttributes attr) {
		Images image = imageRepo.findByUserId(id);
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
	
	public void getProfileImage(Long id, Model model) {
		model.addAttribute("id", id);
		if(imageRepo.existsByUserId(id)) {
			Images profileImage =  imageRepo.findByUserId(id);
			model.addAttribute("ImageName", profileImage.getImageName());
			model.addAttribute("ImageURL", profileImage.getImageURL());			
		}
	}
	
	public void deleteProfileImage(Long id) {
		Images image =  imageRepo.findByUserId(id);
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
