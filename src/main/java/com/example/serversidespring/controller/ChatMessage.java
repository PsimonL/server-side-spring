package com.example.serversidespring.controller;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ChatMessage {
    private String content;
    private String sender;
    private MessageType type;
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
