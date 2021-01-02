package com.example.restagram.domain.users;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
// 따로 직력화 적용위한 테스트 -작성자:김병연.
@ToString
@Getter
public class SessionUser implements Serializable {
    private String username;
    private String eamil;

    public SessionUser(Users user)
    {
        this.username=user.getUsername();
        this.eamil=user.getEmail();
    }
}
