package com.example.restagram.web.userDto;

import com.example.restagram.domain.users.Users;
import lombok.Getter;

@Getter
public class UserListResponseDto {
    private String username;
    private String name;
    private String email;
    private String PhoneNum;

    public UserListResponseDto(Users user) {
        this.username = user.getUsername();
        this.name = user.getName();
        this.email = user.getEmail();
        this.PhoneNum =user.getPhoneNum() ;
    }
}
