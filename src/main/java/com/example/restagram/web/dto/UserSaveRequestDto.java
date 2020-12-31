package com.example.restagram.web.dto;

import com.example.restagram.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@RequiredArgsConstructor
public class UserSaveRequestDto {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private String username;
    private String userId;
    private String password;
    private String email;
    private String phoneNum;
    private String intro;
    private String profileImage;
    private String role;
//    private String role;
    @Builder
    public UserSaveRequestDto(PasswordEncoder passwordEncoder, String username, String userId, String password, String email, String phoneNum, String intro, String profileImage) {
        this.passwordEncoder = passwordEncoder;
        this.username = username;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
        this.intro = intro;
        this.profileImage = profileImage;
        this.role="ROLE_USER";
    }

    public Users toEntity() {

        return Users.builder().name(username).userId(userId)
                .password(password).intro(intro).email(email)
                .phoneNum(phoneNum).profileImage(profileImage).build();
    }

}
