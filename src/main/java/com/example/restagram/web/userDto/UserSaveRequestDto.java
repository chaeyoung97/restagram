package com.example.restagram.web.userDto;

import com.example.restagram.domain.users.Role;
import com.example.restagram.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class UserSaveRequestDto {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;
//    private String role= Role.USER.getKey(); // 일반사용자 자동.

    public void setPassword(String password) {
        this.password = password;
    }

    //    private String role;
    @Builder
    public UserSaveRequestDto(Long id, String name, String username, String password, String email, String phoneNum, String intro, String profileImage) {
        this.id=id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Users toEntity() {
        return Users.builder().name(name).username(username)
                .password(password).email(email)
                .build();
    }

}
