
package com.example.restagram.domain.tables;

import com.example.restagram.domain.tables.FollowTable;
import com.example.restagram.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface FollowTableRepository extends JpaRepository<FollowTable, Long>{
    Optional<FollowTable> findByFromUserAndToUser(Users fromUser, Users toUser);
}

