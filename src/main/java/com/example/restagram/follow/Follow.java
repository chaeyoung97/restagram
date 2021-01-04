package com.example.restagram.follow;

import com.example.restagram.domain.users.Users;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
public class Follow {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	//팔로우 시퀀스
	
	//fromUser가 toUser를 follow함
	@ManyToOne
	@JoinColumn(name="fromUserId")
	private Users fromUser;
	
	//toUser를 fromUser가 follower함
	@ManyToOne
	@JoinColumn(name="toUserId")
	private Users toUser;
	
	//follow, follower 창에서 로그인 한 사용자의 팔로우 상태 확인
	@Transient	//DB에 안 들어가게 함
	private boolean followState;
	
	@CreationTimestamp	//자동으로 현재 시간이 세팅됨
	private Timestamp createDate;
	
	@CreationTimestamp	//자동으로 현재 시간이 세팅됨
	private Timestamp updateDate;
}
