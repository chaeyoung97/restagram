package com.example.restagram.domain.users;

import javax.persistence.*;

import com.example.restagram.domain.BaseTimeEntity;
import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.tables.FollowTable;
import com.example.restagram.domain.tables.LikesTables;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Getter
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Entity
public class Users extends BaseTimeEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getFollowSize(){
		return new Long(follow.size());
	}
	public Long getFollowerSize(){
		return new Long(follower.size());
	}
	public Long getPostSize() {return new Long (posts.size());}
	
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
	@OrderBy("createdDate desc")	//최근 게시물을 맨 앞으로 하기위해 내림차순 정렬
	@OneToMany(mappedBy = "user" ,fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Posts> posts;

	@OneToMany(mappedBy = "user" ,fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LikesTables> likes;

	@OneToMany(mappedBy = "fromUser" ,fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<FollowTable> follow;

	@OneToMany(mappedBy = "toUser" ,fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<FollowTable> follower;
	
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
		this.phoneNum = "";
		this.intro = "";
	}

	public void update(Users newUsers) {
		this.name=newUsers.name;
		this.email=newUsers.email;
		this.phoneNum=newUsers.phoneNum;
		this.intro=newUsers.intro;
		//this.profileImage=newUsers.profileImage;
	}

	public boolean liked(Posts posts){
		return this.likes.stream().filter(a -> a.getPost().equals(posts)).findFirst().isPresent();

	}
}
