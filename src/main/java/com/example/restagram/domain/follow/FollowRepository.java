
package com.example.restagram.domain.follow;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long>{

    Long deleteByFromUserIdAndToUserId(Long fromUser, Long toUser);
    List<Follow> findByFromUser(Long id);
    List<Follow> findByToUser(Long id);
}

