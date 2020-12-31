package com.example.restagram.web;

import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.users.UsersRepository;
import com.example.restagram.service.UserService;
import com.example.restagram.web.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController // return 값이 template 주소가 아닌 객체 또는 아이디 값으로 확인하는 Api 용 Controller 분할.
public class UserApiController {
    private final UserService userService;

    //기본 회원가입을 하는 페이지로 이동하고 로직을 한번에 처리하는 것을 분리.
    //페이지 이동은 getMapping 부분에서 이루어지는 Controller 이고 RestController는 APi 기능 (가입승인, follow승인, 등등.)
    // 눈에 보이지 않는 백엔드 부분.
    @PostMapping("")
    public Long insta_user(@RequestBody UserSaveRequestDto requestDto) {
        return userService.save(requestDto);
    }

}
