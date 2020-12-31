package com.example.restagram.domain.users;

import javax.persistence.*;

import com.example.restagram.domain.BaseTimeEntity;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

@NoArgsConstructor
@ToString
@Getter
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Entity
public class Users extends BaseTimeEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(nullable=false)
	private String userId;
	private String password;
	private String email;
	private String phoneNum;
	private String intro;
	private String profileImage;
	private String role;

	@Builder
	public Users(String name, String userId, String password, String email, String phoneNum, String intro, String profileImage) {
		this.name = name;
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.phoneNum = phoneNum;
		this.intro = intro;
		this.profileImage = profileImage;
		this.role="ROLE_USER";
	}

	public void update(Users newUsers) {
		this.name=name;
		this.password=password;
		this.email=email;
		this.phoneNum=phoneNum;
		this.intro=intro;
		this.profileImage=profileImage;
	}

}

