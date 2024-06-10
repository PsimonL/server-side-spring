package com.example.serversidespring.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.StompWebSocketEndpointRegistration;
import org.springframework.web.socket.config.annotation.SockJsServiceRegistration;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class WebSocketConfigTest {

    @Test
    void registerStompEndpoints() {
        StompEndpointRegistry registry = mock(StompEndpointRegistry.class);
        StompWebSocketEndpointRegistration registration = mock(StompWebSocketEndpointRegistration.class);
        SockJsServiceRegistration sockJsServiceRegistration = mock(SockJsServiceRegistration.class);

        when(registry.addEndpoint(anyString())).thenReturn(registration);
        when(registration.setAllowedOrigins(anyString())).thenReturn(registration);
        when(registration.withSockJS()).thenReturn(sockJsServiceRegistration);

        WebSocketConfig webSocketConfig = new WebSocketConfig();
        webSocketConfig.registerStompEndpoints(registry);

        verify(registry).addEndpoint("/ws");
        verify(registration).setAllowedOrigins("http://localhost:3000");
        verify(registration).withSockJS();
    }

    @Test
    void configureMessageBroker() {
        MessageBrokerRegistry registry = mock(MessageBrokerRegistry.class);

        WebSocketConfig webSocketConfig = new WebSocketConfig();
        webSocketConfig.configureMessageBroker(registry);

        verify(registry).setApplicationDestinationPrefixes("/app");
        verify(registry).enableSimpleBroker("/topic");
    }
}
