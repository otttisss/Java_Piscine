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

public class Program {
    public static void main(String[] args) throws SQLException {
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(hikariCPData());
        Optional<Message> messageOptional = messagesRepository.findById(1L);

        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setText("Abra kadabra");
            message.setTimestamp(null);
            messagesRepository.update(message);
            messageOptional = messagesRepository.findById(1L);
            System.out.println("Updated message: " + messageOptional.get().getText());
        } else {
            System.err.println("\nMessage not found");
        }
    }

    public static DataSource hikariCPData() {
        HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/chat");
        hikariDataSource.setUsername("cmilagro");
        hikariDataSource.setPassword("");

        return hikariDataSource;
    }
}

