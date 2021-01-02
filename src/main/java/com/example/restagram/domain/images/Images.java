package com.example.restagram.domain.images;

import javax.persistence.*;

import com.example.restagram.domain.BaseTimeEntity;

import com.example.restagram.domain.posts.Posts;
import lombok.Builder;
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

	@ManyToOne
	@JoinColumn(name="post_id")
	private Posts post;

	 private Long userId;
	 
	 public void update(String imageName, String imageURL, Long userId) {
		this.imageName = imageName;
		this.imageURL = imageURL;
		this.userId = userId;
	 }

	 @Builder
	 public Images(String imageName, String imageURL, Posts posts, Long userId){
	 	this.imageName = imageName;
	 	this.imageURL = imageURL;
	 	this.post = posts;
	 	this.userId = userId;
	 }
}
