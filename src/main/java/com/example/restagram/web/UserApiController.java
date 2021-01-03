package com.example.restagram.web;

import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.users.UsersRepository;
import com.example.restagram.service.UserService;
import com.example.restagram.web.userDto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/users")
@RequiredArgsConstructor
@RestController // return 값이 template 주소가 아닌 객체 또는 아이디 값으로 확인하는 Api 용 Controller 분할.
public class UserApiController {
    private final UserService userService;

    //기본 회원가입을 하는 페이지로 이동하고 로직을 한번에 처리하는 것을 분리.
    //페이지 이동은 getMapping 부분에서 이루어지는 Controller 이고 RestController는 APi 기능 (가입승인, follow승인, 등등.)
    // 눈에 보이지 않는 백엔드 부분.

    @Autowired
    UsersRepository usersRepository;

    //회원가입 페이지.
    @PostMapping("/api/create")
    public Long insta_user(@RequestBody UserSaveRequestDto requestDto) {
        System.out.println(">>>>>>>>>>>>>>>"+requestDto.toString());
        return userService.save(requestDto);
    }

    // 회원정보 수정 탈퇴 기능api추가.

    //회원 정보 수정
    @PostMapping("/{id}")
    public Long update(@PathVariable Long id, Users newUsers) {
        Users users =usersRepository.findById(id).get();
        users.update(newUsers);
        return Long.valueOf(12);
    }
    // 탈퇴(삭제기능)




}
