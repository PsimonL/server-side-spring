package com.example.serversidespring.service;

import com.example.serversidespring.model.Room;
import com.example.serversidespring.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class ChatServiceTest {

    @Autowired
    private ChatService chatService;

    @MockBean
    private RoomRepository roomRepository;

    @BeforeEach
    void setUp() {
        Room room = new Room();
        room.setId(1L);
        room.setName("Room 1");

        when(roomRepository.findById(anyLong())).thenReturn(Optional.of(room));
        when(roomRepository.save(Mockito.any(Room.class))).thenReturn(room);
    }

    @Test
    void testGetOrCreateRoom() {
        Room room = chatService.getOrCreateRoom(1L);
        assertEquals("Room 1", room.getName());
    }
}
