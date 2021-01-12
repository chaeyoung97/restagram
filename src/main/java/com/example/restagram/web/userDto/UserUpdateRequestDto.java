package com.example.restagram.web.userDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String name;
    private String email;
    private String phoneNum;
    private String intro;

    @Builder
    public UserUpdateRequestDto(String intro,String name, String email, String phoneNum) {
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.intro=intro;
    }
}
