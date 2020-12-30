//package com.example.restagram.Domain;
//
//import com.example.restagram.domain.users.Users;
//import com.example.restagram.domain.users.UsersRepository;
//import org.junit.After;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UsersRepositoryTest {
//
//    @Autowired
//    UsersRepository usersRepository;
//
//    @After
//    public void clean()
//    {
//        usersRepository.deleteAll();
//    }
//
//    @Test
//    public void repositoryTest()
//    {
//        String username="KimByeonYeon";
//
//        usersRepository.save(Users.builder().userName(username).build());
//
//        List<Users> userList= usersRepository.findAll();
//        assertThat(userList.get(0).getUserName()).isEqualTo(username);
//    }
//
//}
