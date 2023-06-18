package edu.school21.chat.models;

import java.time.*;
public class Message {
    private int id;
    private User author;
    private Chatroom room;
    private String text;
    private LocalDateTime timestamp;

    public Message(int id, User author, Chatroom room, String text, LocalDateTime timestamp) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.timestamp = timestamp;
    }
}
