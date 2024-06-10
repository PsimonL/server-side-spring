package com.example.serversidespring.config;

import com.example.serversidespring.model.ChatMessage;
import com.example.serversidespring.model.ChatMessage.MessageType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Map;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@SpringBootTest
class WebSocketEventListenerTest {

    @Configuration
    @ComponentScan(basePackageClasses = {WebSocketEventListener.class})
    static class TestConfig {
        @Bean
        @Primary
        public SimpMessagingTemplate simpMessagingTemplate() {
            return Mockito.mock(SimpMessagingTemplate.class);
        }
    }

    @Autowired
    private WebSocketEventListener webSocketEventListener;

    @MockBean
    private SimpMessageSendingOperations messagingTemplate;

    @Test
    void testHandleWebSocketDisconnectListener() {
        SessionDisconnectEvent event = Mockito.mock(SessionDisconnectEvent.class);
        String username = "testUser";

        Mockito.when(event.getMessage().getHeaders().get("simpSessionAttributes"))
                .thenReturn(Map.of("username", username));

        webSocketEventListener.handleWebSocketDisconnectListener(event);

        ChatMessage chatMessage = ChatMessage.builder()
                .type(MessageType.LEAVE)
                .sender(username)
                .build();

        verify(messagingTemplate).convertAndSend(eq("/topic/public"), eq(chatMessage));
    }
}
