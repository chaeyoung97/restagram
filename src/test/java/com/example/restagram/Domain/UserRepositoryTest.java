package com.example.restagram.Domain;

import com.example.restagram.domain.Users.User;
import com.example.restagram.domain.Users.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @After
    public void clean()
    {
        userRepository.deleteAll();
    }

    @Test
    public void repositoryTest()
    {
        String username="KimByeonYeon";

        userRepository.save(User.builder().userName(username).build());

        List<User> userList=userRepository.findAll();
        assertThat(userList.get(0).getUserName()).isEqualTo(username);
    }

}
