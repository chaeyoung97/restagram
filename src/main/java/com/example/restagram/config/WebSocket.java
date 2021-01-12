package com.example.restagram.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocket implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp").withSockJS(); // .setAllowedOriginPatterns("*").withSockJS(); 최조 소켓열때 이름 ws-stomp
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {   // 클라이언트로 메시지 응답해 줄때. prefix를 정의
        registry.enableSimpleBroker("/sub");   //topic  // 클라이언트로 메세지를 응답해 줄때 프리픽스
        registry.setApplicationDestinationPrefixes("/pub");    // app  // 클라이언트에서 메시지 송신시 프리픽스

    }

}