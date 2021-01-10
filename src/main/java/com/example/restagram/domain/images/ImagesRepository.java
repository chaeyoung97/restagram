package com.example.restagram.domain.images;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ImagesRepository extends JpaRepository<Images, Long>{
	@Transactional
	Images findByUserId(Long userId);
	Images findByPostId(Long postId);
	List<Images> findAllByPostId(Long postId);
	List<Images> findAllByUserIdAndImageNameNot(Long userId,String imageName);
	Images findByUserIdAndImageName(Long userId, String imageName);
	boolean existsByUserIdAndImageName(Long userId,String imageName);
	boolean existsByPostId(Long postId);
}
