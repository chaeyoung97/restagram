package com.example.demo.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id; 
	
	private String name;
	
	@Column(nullable=false)
	private String userId;
	private String password;
	private String email;
	private String phoneNum;
	private String intro;
	private String profileImage;
	
	@CreationTimestamp
	private Timestamp createDate;
	
	@CreationTimestamp
	private Timestamp updateDate;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", userId=" + userId + ", password=" + password + ", email="
				+ email + ", phoneNum=" + phoneNum + ", intro=" + intro + ", profileImage=" + profileImage + "]";
	}

	public void update(User newUser) {
		this.name=name;
		this.password=password;
		this.email=email;
		this.phoneNum=phoneNum;
		this.intro=intro;
		this.profileImage=profileImage;
	}
	
}
