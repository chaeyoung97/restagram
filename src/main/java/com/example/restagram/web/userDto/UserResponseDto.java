package com.example.restagram.web.userDto;

import com.example.restagram.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class UserResponseDto {
    private Long id;

    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    @Builder
    public UserResponseDto(Long id, String password) {
        this.id=id;
        this.password = password;
    }

    public Users toEntity() {

        return Users.builder()
                .password(password)
                .build();
    }

}
