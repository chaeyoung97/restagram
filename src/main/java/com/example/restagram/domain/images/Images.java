package com.example.restagram.domain.images;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.example.restagram.domain.BaseTimeEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Images extends BaseTimeEntity{
	 @Column(name = "image_id")
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Id
	 private Long id;
	 
	 private String imageName;
	 
	 @Lob
	 private String imageURL;
	 
	 private Long postId;
	 
	 private Long userId;
	 
	 public void update(String imageName, String imageURL, Long postId, Long userId) {
		this.imageName = imageName;
		this.imageURL = imageURL;
		this.postId = postId;
		this.userId = userId;
	 }
}
