package com.example.restagram.config;

import com.example.restagram.domain.users.Role;
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
//
        http.csrf()
                .disable().headers().frameOptions().disable().and()
                .authorizeRequests()
                .antMatchers("/signUpForm","/loginForm","/h2-console/*").permitAll()
//                .antMatchers("").hasRole("USER") // 사용자 접근 url
                .antMatchers("/admin/userList").hasRole("ADMIN") // 회원 정보 list 관리자 권한 승인한자만 사용가능.
//                .antMatchers("").authenticated() // 로그인이 필요하다. 이 부분은 api 부분의 url 적는 곳.
//                .anyRequest() // 기타 url 모두 허용.
//                .authenticated()
                .and()
                .formLogin()//로그인페이지 사용
                    .loginPage("/loginForm")
                    .loginProcessingUrl("/login") // 로그인 실제 이루어지는 곳. // 이곳은 스프링 시큐리티 자체에서 제공. 만들필요 x.
                    .defaultSuccessUrl("/")
                .and()
                .logout()
                    .logoutUrl("/logout")
                .logoutSuccessUrl("/loginForm");
    }
}
