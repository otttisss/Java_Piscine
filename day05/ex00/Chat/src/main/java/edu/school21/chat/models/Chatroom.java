package edu.school21.chat.models;

import java.util.List;

public class Chatroom {
    private int id;
    private String room_name;
    private User owner;
    private List<Message> messageList;

    public Chatroom(int id, String room_name, User owner, List<Message> messageList) {
        this.id = id;
        this.room_name = room_name;
        this.owner = owner;
        this.messageList = messageList;
    }
}
