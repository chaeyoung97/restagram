package com.example.restagram.domain.users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long>{
	Users findByName(String userName);
}
