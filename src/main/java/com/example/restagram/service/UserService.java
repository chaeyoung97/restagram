package com.example.restagram.service;

import com.example.restagram.config.PrincipalDetail;
import com.example.restagram.domain.users.SessionUser;
import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.users.UsersRepository;
import com.example.restagram.web.userDto.UserListResponseDto;
import com.example.restagram.web.userDto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private final PasswordEncoder encrpyt;

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
            requestDto.setPassword(encrpyt.encode(requestDto.getPassword()));
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


}
