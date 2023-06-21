package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Chatroom {
    private Long id;
    private String room_name;
    private User owner;
    private List<Message> messageList;

    public Chatroom(Long id, String room_name, User owner, List<Message> messageList) {
        this.id = id;
        this.room_name = room_name;
        this.owner = owner;
        this.messageList = messageList;
    }

    public Chatroom(Long id, String room_name) {
        this.id = id;
        this.room_name = room_name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, room_name, owner, messageList);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Chatroom chatroom = (Chatroom) obj;
        if (id != chatroom.id)
            return false;
        if (owner != chatroom.owner)
            return false;
        if (!Objects.equals(room_name, chatroom.room_name))
            return false;
        return Objects.equals(messageList, chatroom.messageList);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", title='" + room_name + '\'' +
                ", owner=" + owner +
                ", messageList=" + messageList +
                "}";
    }

    public Long getId() {
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

    public void setId(Long id) {
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

