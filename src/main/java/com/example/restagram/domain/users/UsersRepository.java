package com.example.restagram.domain.users;
import org.h2.engine.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long>{
//	Users findByUserName(String userName);
	Optional<Users> findById(String userId);
//	Users findByUserId(String userId);
}
