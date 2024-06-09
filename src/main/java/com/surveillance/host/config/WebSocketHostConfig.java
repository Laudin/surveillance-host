package com.surveillance.host.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.surveillance.host.handler.WebSocketHostHandler;

@Configuration
@EnableWebSocket
public class WebSocketHostConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/surveillance/proxy").setAllowedOrigins("*");
        registry.addHandler(webSocketHandler(), "/surveillance/client").setAllowedOrigins("*");

    }

    @Bean
    public WebSocketHostHandler webSocketHandler() {
        return new WebSocketHostHandler();
    }

}
