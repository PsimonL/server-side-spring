package com.example.serversidespring.service;

import com.example.serversidespring.model.Room;
import com.example.serversidespring.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    private RoomRepository roomRepository;

    public Room getOrCreateRoom(Long roomId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room == null) {
            room = new Room();
            room.setId(roomId);
            room.setName("Room " + roomId);
            roomRepository.save(room);
        }
        return room;
    }
}
