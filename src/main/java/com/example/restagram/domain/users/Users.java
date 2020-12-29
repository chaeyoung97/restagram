//package com.example.restagram.domain.Users;
//
//import java.sql.Timestamp;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//
//import com.example.restagram.domain.BaseTimeEntity;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Setter
//@Entity
//public class Users extends BaseTimeEntity {
//	@Id
//	@GeneratedValue
//	private Long id;
//
//	private String name;
//
//	@Column(nullable=false)
//	private String userId;
//	private String password;
//	private String email;
//	private String phoneNum;
//	private String intro;
//	private String profileImage;
//
//	@Override
//	public String toString() {
//		return "User [id=" + id + ", name=" + name + ", userId=" + userId + ", password=" + password + ", email="
//				+ email + ", phoneNum=" + phoneNum + ", intro=" + intro + ", profileImage=" + profileImage + "]";
//	}
//
//	public void update(Users newUsers) {
//		this.name=name;
//		this.password=password;
//		this.email=email;
//		this.phoneNum=phoneNum;
//		this.intro=intro;
//		this.profileImage=profileImage;
//	}
//
//}

package com.example.restagram.domain.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.example.restagram.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
public class Users extends BaseTimeEntity {
	@Id
	@GeneratedValue
	Long id;

	String userName;

	@Builder
	public Users(String userName){
		this.userName= userName;
	}
}
