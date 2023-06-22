package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;
    private List<Chatroom> createdRooms;
    private List<Chatroom> usedRoom;

    public User(Long id, String login, String password, List<Chatroom> createdRooms, List<Chatroom> usedRoom) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.usedRoom = usedRoom;
    }

    public User(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createdRooms, usedRoom);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        User user = (User) obj;

        return Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(password, user.password) &&
                Objects.equals(createdRooms, user.createdRooms) && Objects.equals(usedRoom, user.usedRoom);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdRooms=" + createdRooms +
                ", userRooms=" + usedRoom +
                '}';
    }

    public Long getId() {
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

    public void setId(Long id) {
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
