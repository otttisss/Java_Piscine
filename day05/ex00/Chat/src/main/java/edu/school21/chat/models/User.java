package edu.school21.chat.models;

import java.util.List;

public class User {
    private int id;
    private String login;
    private String password;
    private List<Chatroom> createdRooms;

    public User(int id, String login, String password, List<Chatroom> createdRooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
    }
}
