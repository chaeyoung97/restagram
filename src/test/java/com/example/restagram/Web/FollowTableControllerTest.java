package com.example.restagram.Web;


import com.example.restagram.domain.tables.FollowTable;
import com.example.restagram.domain.tables.FollowTableRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FollowTableControllerTest {

    @Autowired
    private FollowTableRepository followTableRepository;

    //follow 테스트
    @Test
    public void followTest() throws Exception {
        List<FollowTable> followTables = followTableRepository.findByFromUser((long)2);
        List<FollowTable> followers= followTableRepository.findByToUser((long)1);


    }

    //unfollow 테스트
    @Test
    public void unfollowTest() throws Exception {
        List<FollowTable> followTables = followTableRepository.findByFromUser((long)2);
        List<FollowTable> followers= followTableRepository.findByToUser((long)1);
    }
}
