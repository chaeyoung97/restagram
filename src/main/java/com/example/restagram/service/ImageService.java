package com.example.restagram.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
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
	// 추후 infra 구축. -작성자:김병연
	public void savePostImages(Long postId, MultipartFile[] files, RedirectAttributes attr) throws IllegalStateException, IOException {
		String path = System.getProperty("user.dir") + "\\bin\\main\\static\\images\\post\\"+ postId.toString();
		File folder = new File(path);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		for(MultipartFile file : files) {
			Long count = imageRepo.count();
			String fName = Long.toString(count+1) + "." + FilenameUtils.getExtension(file.getOriginalFilename());
			String fURL = path + "\\" + fName;
			File destFile = new File(fURL);
			file.transferTo(destFile);
			Images image = new Images();
			image.update(fName, "/images/post/"+postId.toString()+"/"+fName, postId, null);
			imageRepo.save(image);
		}
		attr.addFlashAttribute("images", imageRepo.findAll());
	}
	
	public void getPostImages(Long postId, Model model) {
		model.addAttribute("postId", postId);
		List<Images> imageList =  imageRepo.findAllByPostId(postId);
		model.addAttribute("images", imageList);
	}
	
	public void updatePostImages(Long postId, MultipartFile[] files, RedirectAttributes attr) throws IllegalStateException, IOException {
		List<Images> imageList = imageRepo.findAllByPostId(postId);
		for(Images image : imageList) {
			String prevURL = System.getProperty("user.dir") + "/bin/main/static"+  image.getImageURL();
			deleteImage(prevURL, image);
		}		
		String path = System.getProperty("user.dir") + "\\bin\\main\\static\\images\\post\\"+ postId.toString();
		File folder = new File(path);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		for(MultipartFile file : files) {
			Long count = imageRepo.count();
			String fName = Long.toString(count+1) + "." + FilenameUtils.getExtension(file.getOriginalFilename());
			String fURL = path + "\\" + fName;
			File destFile = new File(fURL);
			file.transferTo(destFile);
			Images image = new Images();			
			image.update(fName, "/images/post/"+postId.toString()+"/"+fName, postId, null);
			imageRepo.save(image);
		}
		
		attr.addFlashAttribute("images", imageRepo.findAll());
	}
	
	public void deletePostImage(Long postId) {
		List<Images> imageList = imageRepo.findAllByPostId(postId);
		for(Images image : imageList) {
			String prevURL = System.getProperty("user.dir") + "/bin/main/static"+  image.getImageURL();
			deleteImage(prevURL, image);
		}		
	}
	
	public void saveProfileImage(Long id, MultipartFile file, RedirectAttributes attr) throws IllegalStateException, IOException {
		String path = System.getProperty("user.dir") + "\\bin\\main\\static\\images\\profile\\"+ id.toString();
		File folder = new File(path);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		String fName = "profile." + FilenameUtils.getExtension(file.getOriginalFilename());
		System.out.println(fName);
		String fURL = path + "\\" + fName;
		
		File destFile = new File(fURL);
		file.transferTo(destFile);
		Images image = new Images();
		image.update(fName, "/images/profile/"+id.toString()+"/"+fName, null, id);
		attr.addFlashAttribute("ImageName", fName);
		attr.addFlashAttribute("ImageURL","/images/profile/"+id.toString()+"/"+fName);
		imageRepo.save(image);
	}
	
	public void updateProfileImage(Long id, MultipartFile file, RedirectAttributes attr) throws IllegalStateException, IOException {
		Images image = imageRepo.findById(id).get();
		deleteImage(System.getProperty("user.dir") + "/bin/main/static"+  image.getImageURL(), image);		
		
		String path = System.getProperty("user.dir") + "\\bin\\main\\static\\images\\profile\\"+ id.toString();
		File folder = new File(path);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		String fName = "profile." + FilenameUtils.getExtension(file.getOriginalFilename());
		String fURL = path + "\\" + fName;
		
		File destFile = new File(fURL);
		file.transferTo(destFile);
		image.update(fName, "/images/profile/"+id.toString()+"/"+fName, null, id);
		
		imageRepo.save(image);
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
		
	}
	
	
	private void deleteImage(String prevURL, Images image) {
		imageRepo.delete(image);
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
}
