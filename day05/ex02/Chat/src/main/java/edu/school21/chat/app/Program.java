package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
public class Program {
    public static void main(String[] args) {
        User creator = new User(6L, "creator", "creator", new ArrayList<>(), new ArrayList<>());
        User author = creator;
        Chatroom room = new Chatroom(6L, "room", creator, new ArrayList<>());
        Message message = new Message(null, author, room, "Hello everyone!", LocalDateTime.now());
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(hikariCPData());
        messagesRepository.save(message);
        System.out.println(message.getId());
    }

    public static DataSource hikariCPData() {
        HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/chat");
        hikariDataSource.setUsername("cmilagro");
        hikariDataSource.setPassword("");

        return hikariDataSource;
    }
}

