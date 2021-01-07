package com.example.restagram.domain.tables;

import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesTablesRepository extends JpaRepository<LikesTables, Long> {

    Optional<LikesTables> findByUserAndPost(Users users, Posts posts);
}
