package com.example.restagram.domain.users;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.example.restagram.domain.BaseTimeEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Users extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(nullable = false)
    private String userId;
    private String password;
    private String email;
    private String phoneNum;
    private String intro;
    private String profileImage;

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", userId=" + userId + ", password=" + password + ", email="
                + email + ", phoneNum=" + phoneNum + ", intro=" + intro + ", profileImage=" + profileImage + "]";
    }

    public void update(Users newUsers) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
        this.intro = intro;
        this.profileImage = profileImage;
    }

}

