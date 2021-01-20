package com.example.restagram.domain.tables;

import com.example.restagram.domain.BaseTimeEntity;
import com.example.restagram.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
@NoArgsConstructor
@Getter
@Entity
public class FollowTable {
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

	@Builder
	public FollowTable(Users fromUser, Users toUser){
		this.fromUser = fromUser;
		this.toUser = toUser;
	}

}
