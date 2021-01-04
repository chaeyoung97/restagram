
package com.example.restagram.follow;

import com.example.restagram.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long>{
    @Transactional
    Long deleteByFromUserIdAndToUserId(Long fromUser, Long toUser);
    List<Follow> findByFromUser(Long id);
    List<Follow> findByToUser(Long id);
}

