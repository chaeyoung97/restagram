package com.example.restagram;

import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;

@EnableJpaAuditing
@Configuration
@SpringBootApplication
public class RestagramApplication {

    public static void main(String[] args) {

        SpringApplication.run(RestagramApplication.class, args);
    }
}
