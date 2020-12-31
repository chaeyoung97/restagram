//package com.example.restagram.security;
//
//import com.example.restagram.domain.users.Users;
//import com.example.restagram.domain.users.UsersRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class InstaUserDetailsService implements UserDetailsService {
//	@Autowired
//	private UsersRepository userRepository;
//
//
//	//loginForm에서 action="user/login"
//	//로그인 버튼을 누르면 SecurityConfig에서 낚아채서 여기서 작동함
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		Users users=userRepository.findByName(username);
//		InstaUserDetails userDetails=null;
//
//		if(users!=null) {
//			userDetails=new InstaUserDetails();
//			userDetails.setUser(users);
//		} else {
//			throw new UsernameNotFoundException("사용자 이름이 존재하지 않습니다. : "+username);
//		}
//
//		return userDetails;
//	}
//
//}
