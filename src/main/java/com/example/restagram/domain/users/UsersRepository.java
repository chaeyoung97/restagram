package com.example.restagram.domain.users;
import com.example.restagram.web.userDto.UserResponseDto;
import com.example.restagram.web.userDto.UserSaveRequestDto;
import org.h2.engine.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long>{
	Optional<Users> findByUsername(String username);

	// 전체 조회 리스트. (생성일자 기준) DSL
	@Query(value = "select u from Users u order by u.createdDate")
	List<Users> findbyAll_createdDate();


//    void withdrawal(UserResponseDto requestDto);
}
