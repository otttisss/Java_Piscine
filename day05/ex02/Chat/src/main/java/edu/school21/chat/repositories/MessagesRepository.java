package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;
import edu.school21.chat.exception.NotSavedSubEntityException;

import java.sql.SQLException;
import java.util.Optional;
public interface MessagesRepository {
    Optional<Message> findById(Long id) throws SQLException;
    boolean save(Message message) throws NotSavedSubEntityException;
}
