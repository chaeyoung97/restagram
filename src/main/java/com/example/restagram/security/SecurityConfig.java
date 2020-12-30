package com.example.restagram.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration	//아래의 어노테이션이 로그인 시도시 낚아채는 역할
@EnableWebSecurity	//스프링 시큐리티 필터에 등록하는 어노테이션
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public BCryptPasswordEncoder pwEncoder() {
			return new BCryptPasswordEncoder();
	}
	
	//csrf(Cross-Site Request Forgery): 사이트 사용자가 자신의 의지와 무관하게 공격자가 의도하는 행위를 특정 웹에 요청하도록 만드는 공격,
	//cors(Cross-Origin Resource Sharing): 클라이언트에서 도메인 혹은 포트번호가 다른 서버의 자원을 요청하는 매커니즘
	//(cors는 그 자체가 공격에 해당하기 보다는 보안 문제와 관련이 깊은 내용에 해당함)
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();	//csrf 토큰 검사를 비활성화하는 로직
		http.cors().disable();	//cors 토큰 검사를 비활성화하는 로직
		http.authorizeRequests()
		.antMatchers("/", "/user/**", "/follow/**")
		.authenticated()
		.anyRequest()
		.permitAll()
		.and()
		.formLogin()	//form을 통한 login을 활성화하고 
		.loginPage("/users/loginForm")	//login이 필요한 페이지에 접근했을 때 사용자에게 보여줄 Custom Login Form Page를 띄워줄 handler의 url을 지정
						//.loginPage()를 설정하지 않는 경우 Spring Security에서 제공하는 기본 Form Login Page가 나타남
		.loginProcessingUrl("/users/login")	//실제 로그인 진행
		.defaultSuccessUrl("/");	//로그인 성공시 보여줄 페이지
	}
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//직접 인코딩하는 것이 아니라, 어떤 인코딩으로 패스워드가 만들어졌는지 알려줌
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(pwEncoder());
	}
}
