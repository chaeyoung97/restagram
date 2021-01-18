package com.example.restagram.service;

import com.example.restagram.config.PrincipalDetail;
import com.example.restagram.domain.users.SessionUser;
import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.users.UsersRepository;
import com.example.restagram.web.userDto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UsersRepository userRepository;
    private final HttpSession httpSession;
    private final PasswordEncoder encrypt;
    private final PostsService postsService;
    private final FollowService followService;
    private final ImageService imageService;
    // 로그인 인증과정.
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Users> optionalUser=userRepository.findByUsername(s);
        if(optionalUser.isPresent())
        {
            Users member=optionalUser.get();
//            System.out.println(member.toString());
            httpSession.setAttribute("sessionUser", new SessionUser(member));
        }
        return optionalUser.map(PrincipalDetail::new).orElse(null);
    }

    // user Create
    @Transactional
    public Long save(UserSaveRequestDto requestDto) {
        if(!userRepository.findByUsername(requestDto.getUsername()).isPresent())
        {
            requestDto.setPassword(encrypt.encode(requestDto.getPassword()));
            return userRepository.save(requestDto.toEntity()).getId();
        }
        else
        {
            System.out.println("--------------------중복된 ID ------------------");
            return null;
        }
    }

    @Transactional(readOnly = true) // 조회속도 개선.
    public List<UserListResponseDto> userlist() {
        return userRepository.findbyAll_createdDate().stream()
                .map(UserListResponseDto::new).collect(Collectors.toList());
    }
    //회원정보 수정
    @Transactional
    public Long update(Long id, UserUpdateRequestDto requestDto)
    {
        Users users=userRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 사용자가 존재하지 않습니다.")
        );
        users.update(requestDto);
        System.out.println("user Update Dirty Checking");
        return id;
    }
    //회원 탈퇴
    @Transactional
    public String withdrawal(UserResponseDto requestDto) throws Exception {
        //프로필 사진 삭제
        imageService.deleteProfileImage(requestDto.getId());

        //탈퇴 진행
//        userRepository.withdrawal(requestDto);

        return "";
    }
    @Transactional
    public void delete(UserDeleteRequestDto requestDto, Long id) {
        imageService.deleteProfileImage(id);
        Users deleteUser=userRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException(" 해당 사용자가 존재하지 않습니다.")
        );
        if(encrypt.matches(requestDto.getPassword(),deleteUser.getPassword()))
        {
            System.out.println(">>>>>>>>>>>>>>>deleteSuccess");
            userRepository.delete(deleteUser);
        }
        else
        {
            System.out.println(">>>>>>>>>>>>>>>>>misMathch");
        }
    }

//    //password 체크
//    @Transactional
//    public int checkPw(UserSaveRequestDto requestDto) {
//        int result=requestDto
//    }

}
