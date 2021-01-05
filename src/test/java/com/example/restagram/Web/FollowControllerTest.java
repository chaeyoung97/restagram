//package com.example.restagram.Web;
//
//
//import com.example.restagram.domain.follow.Follow;
//import com.example.restagram.domain.follow.FollowRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class FollowControllerTest {
//
//    @Autowired
//    private FollowRepository followRepository;
//
//    //follow 테스트
//    @Test
//    public void followTest() throws Exception {
//        List<Follow> follows=followRepository.findByFromUser((long)2);
//        List<Follow> followers=followRepository.findByToUser((long)1);
//
//
//    }
//
//    //unfollow 테스트
//    @Test
//    public void unfollowTest() throws Exception {
//        List<Follow> follows=followRepository.findByFromUser((long)2);
//        List<Follow> followers=followRepository.findByToUser((long)1);
//    }
//}
