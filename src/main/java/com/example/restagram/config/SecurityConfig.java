package com.example.restagram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // 암호화 password
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/h2-console/*").permitAll();// h2 데이터베이스 접근 허용.
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/signUpForm","/loginForm").permitAll()
//                .antMatchers("").authenticated() // 로그인이 필요하다. 이 부분은 api 부분의 url 적는 곳.
                .anyRequest() // 기타 url 모두 허용.
                .permitAll()
                .and()
                .formLogin()//로그인페이지 사용
                .loginPage("/users/loginForm")
                .loginProcessingUrl("/login") // 로그인 실제 이루어지는 곳. // 이곳은 스프링 시큐리티 자체에서 제공. 만들필요 x.
                .defaultSuccessUrl("/");
    }
}
