
package com.example.restagram.follow;

import com.example.restagram.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long>{
    Follow deleteByFromUserAndToUserID(Long fromUser, Long toUser);
    List<Follow> findByFromUser(Long id);
    List<Follow> findByToUser(Long id);
}

