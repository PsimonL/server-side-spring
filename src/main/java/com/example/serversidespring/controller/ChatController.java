package com.example.serversidespring.controller;

import com.example.serversidespring.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.example.serversidespring.service.ChatService;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @MessageMapping("/message/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage, @DestinationVariable Long roomId) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor, @DestinationVariable Long roomId) {
        chatService.getOrCreateRoom(roomId);
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
