package com.example.serversidespring.config;

import com.example.serversidespring.controller.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {
    private final SimpMessageSendingOperations messageTempalte;
    @EventListener
    public void HandleWebScoketDisconnectListener(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
//        String username = (String) headerAccessor.<String>getSessionAttributes().get("username");

        if(username != null){
            log.info("User {} disconnected!", username);
            var chatMessage = ChatMessage.builder().type(ChatMessage.MessageType.LEAVE).sender(username).build();
            messageTempalte.convertAndSend("/topic/public", chatMessage);
        } else {
            log.info("User {} NullPointerException", username);
        }

    }
}
