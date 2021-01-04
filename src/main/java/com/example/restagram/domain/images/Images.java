package com.example.restagram.domain.images;

import java.io.Serializable;

import javax.persistence.*;

import com.example.restagram.domain.BaseTimeEntity;

import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.users.Users;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Images extends BaseTimeEntity implements Serializable {
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
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users user;
	 
	 public void update(String imageName, String imageURL) {//userId는 user객체로 변경하였음. save시에만 적용됨.
		this.imageName = imageName;
		this.imageURL = imageURL;
	 }

	 @Builder
	 public Images(String imageName, String imageURL, Posts post, Users user){
	 	this.imageName = imageName;
	 	this.imageURL = imageURL;
	 	this.post = post;
	 	this.user = user;
	 }
}
