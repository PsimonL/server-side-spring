package com.example.serversidespring.controller;

import com.example.serversidespring.model.ChatMessage;
import com.example.serversidespring.service.ChatService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

@WebMvcTest(ChatController.class)
class ChatControllerTest {

    @Autowired
    private ChatController chatController;

    @MockBean
    private ChatService chatService;

    @Test
    void testSendMessage() {
        ChatMessage message = new ChatMessage();
        message.setContent("Hello");

        ChatMessage response = chatController.sendMessage(message, 1L);
        assertEquals("Hello", response.getContent());
    }

    @Test
    void testAddUser() {
        ChatMessage message = new ChatMessage();
        message.setSender("testUser");

        SimpMessageHeaderAccessor headerAccessor = Mockito.mock(SimpMessageHeaderAccessor.class);
        Mockito.when(headerAccessor.getSessionAttributes()).thenReturn(new HashMap<>());

        chatController.addUser(message, headerAccessor, 1L);

        verify(chatService).getOrCreateRoom(anyLong());
        assertEquals("testUser", headerAccessor.getSessionAttributes().get("username"));
    }
}
