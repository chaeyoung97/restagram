package com.example.restagram.Web.ApiControllerTest;


import com.example.restagram.domain.users.Role;
import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.users.UsersRepository;
import com.example.restagram.web.userDto.*;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiControlellerTest {
    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

   /* @After
    public void clean() throws Exception{
        usersRepository.deleteAll();
    }*/


    @Test
    public void 유저등록() throws Exception{

         String username="kimByeongYeon";
         String userId="kBY";
         String password="1234";
         String email="admin@naver.con";
         String phoneNum="010-1111-1111";
         String intro="intro";
         String profileImage="profileImage";
        password=passwordEncoder.encode(password);
        UserSaveRequestDto requestDto=UserSaveRequestDto.builder()
                .email(email).username(userId).intro(intro)
                .username(username).password(password).phoneNum(phoneNum)
                .profileImage(profileImage).build();


        String url="http://localhost:"+port+"";
        ResponseEntity<Long> responseEntity=restTemplate.postForEntity(url,requestDto,Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Users> arr=usersRepository.findAll();
        System.out.println(arr.get(0));
        assertThat(arr.get(0).getUsername()).isEqualTo(userId);
        assertThat(arr.get(0).getPassword()).isEqualTo(password);
    }


}
