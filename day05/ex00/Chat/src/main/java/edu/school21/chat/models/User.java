package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String login;
    private String password;
    private List<Chatroom> createdRooms;
    private List<Chatroom> usedRoom;

    public User(int id, String login, String password, List<Chatroom> createdRooms, List<Chatroom> usedRoom) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.usedRoom = usedRoom;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createdRooms);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Chatroom> getCreatedRooms() {
        return createdRooms;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreatedRooms(List<Chatroom> createdRooms) {
        this.createdRooms = createdRooms;
    }
}
