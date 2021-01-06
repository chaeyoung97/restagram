package com.example.restagram.domain.users;

import javax.persistence.*;

import com.example.restagram.domain.BaseTimeEntity;
import com.example.restagram.domain.posts.Posts;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
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

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

//	프로필에서 사용자가 작성한 게시글 목록을 불러오기 위해 양방향 매핑을 해줌
	@OrderBy("createdDate")
	@OneToMany(mappedBy = "user" ,fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Posts> posts;

	@Builder
	public Users(String name, String username, String password, String email) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		if(name.equals("admin"))
			this.role=Role.ADMIN;
		else
			this.role = Role.USER;
		profileImage = "/imgs/defaultImage.jpg";
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
