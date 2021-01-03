package com.example.restagram.domain.users;

import javax.persistence.*;

import com.example.restagram.domain.BaseTimeEntity;
import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.tables.TagsTables;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.util.List;


@NoArgsConstructor
@ToString
@Getter
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Entity
public class Users extends BaseTimeEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Column(nullable=false)
	private String username;
	private String password;
	private String name;
	private String email;
	private String phoneNum;

	@Column(nullable = true)
	private String intro;

	@Column(nullable = true)
	private String profileImage;

	@Column(nullable = false)
	private String role;

	//프로필에서 사용자가 작성한 게시글 목록을 불러오기 위해 양방향 매핑을 해줌
	@OrderBy("createdDate")
	@OneToMany(mappedBy = "user" ,fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Posts> posts ;
	
	@Builder
	public Users(String name, String username, String password, String email, String phoneNum, String intro, String profileImage) {
		this.name = name;
		this.username = username;
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
