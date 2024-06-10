package com.example.serversidespring.controller;

import com.example.serversidespring.model.ChatMessage;
import com.example.serversidespring.model.Room;
import com.example.serversidespring.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChatControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RoomRepository roomRepository;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port;
        roomRepository.deleteAll();
    }

    @Test
    void testSendMessageAndAddUser() {
        Room room = new Room();
        room.setName("Room 1");
        roomRepository.save(room);

        ChatMessage message = new ChatMessage();
        message.setContent("Hello");
        message.setSender("testUser");

        System.out.println("Base URL: " + baseUrl);

        // Test send message
        ResponseEntity<ChatMessage> sendMessageResponse = restTemplate.postForEntity(baseUrl + "/chat/message/" + room.getId(), message, ChatMessage.class);
        System.out.println("Send Message Response: " + sendMessageResponse);
        assertThat(sendMessageResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(sendMessageResponse.getBody().getContent()).isEqualTo("Hello");

        // Test add user
        ResponseEntity<ChatMessage> addUserResponse = restTemplate.postForEntity(baseUrl + "/chat/addUser/" + room.getId(), message, ChatMessage.class);
        System.out.println("Add User Response: " + addUserResponse);
        assertThat(addUserResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(addUserResponse.getBody().getSender()).isEqualTo("testUser");
    }
}
