package com.example.restagram.web.userDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserDeleteRequestDto {
    private String password;

    @Builder

    public UserDeleteRequestDto(String password) {
        this.password = password;
    }
}
