package com.example.restagram.domain.images;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesRepository extends JpaRepository<Images, Long>{
	Images findByUserId(Long userId);
	Images findByPostId(Long postId);
	List<Images> findAllByPostId(Long userId);
	Images findByUserIdAndImageName(Long userId, String imageName);
	boolean existsByUserId(Long userId);
	boolean existsByPostId(Long postId);
}
