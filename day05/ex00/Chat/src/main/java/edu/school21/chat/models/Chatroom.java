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

    public int getId() {
        return id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public User getOwner() {
        return owner;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
