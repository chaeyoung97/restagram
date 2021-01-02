package com.example.restagram.domain.users;
import org.h2.engine.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long>{
	Optional<Users> findByUsername(String username);
//	Users findByUsername(String username);

}
