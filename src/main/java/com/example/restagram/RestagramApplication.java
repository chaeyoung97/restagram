package com.example.restagram;

import com.example.restagram.domain.posts.Posts;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@Configuration
@SpringBootApplication
public class RestagramApplication {

    public static void main(String[] args) {

        SpringApplication.run(RestagramApplication.class, args);

        String s="abc";
        Posts post=new Posts(s);

        post.getId();

    }

}
