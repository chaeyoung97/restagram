package com.example.restagram.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.restagram.domain.images.Images;
import com.example.restagram.domain.images.ImagesRepository;

@Service
public class ImageService {
	@Autowired
	private ImagesRepository imageRepo;
	// 추후 infra 구축. -작성자:김병연
	public void savePostImages(Long postId, MultipartFile[] files) throws IllegalStateException, IOException {
		String path = System.getProperty("user.dir") + "\\bin\\main\\static\\images\\post\\"+ postId.toString();
		File folder = new File(path);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		for(MultipartFile file : files) {
			String fName = file.getOriginalFilename();
			String fURL = path + "\\" + fName;
			File destFile = new File(fURL);
			file.transferTo(destFile);
			Images image = new Images();
			image.setImageName(fName);
			image.setImageURL("/images/post/"+postId.toString()+"/"+fName);
			imageRepo.save(image);
		}
	}
	
}
