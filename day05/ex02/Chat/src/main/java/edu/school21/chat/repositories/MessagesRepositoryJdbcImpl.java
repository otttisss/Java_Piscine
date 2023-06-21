package edu.school21.chat.repositories;

import edu.school21.chat.exception.NotSavedSubEntityException;
import edu.school21.chat.models.*;

import java.sql.*;
import java.util.Optional;
import javax.sql.DataSource;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final DataSource dataSource;
    private final String queryMessage = "select * from chat.messages where id = ";
    private final String queryUser = "select * from chat.users where id = ";
    private final String queryChatroom = "select * from chat.rooms where id = ";

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean save(Message message) throws NotSavedSubEntityException {
        String insertQuery = "insert into chat.messages (id, author, room, message, timestamp)" +
                " values (" + message.getAuthor().getId() + "," +
                message.getRoom().getId() + ", " +
                message.getText() + ", " +
                Timestamp.valueOf(message.getTimestamp()) + ");";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            message.setId(resultSet.getLong("id"));
        } catch (SQLException e) {

        }
        return false;
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(queryMessage + id);

            if (!resultSet.next())
                return Optional.empty();

            Long userId = resultSet.getLong(2);
            Long roomId = resultSet.getLong(3);
            User user = findUser(userId);
            Chatroom chatroom = findChatroom(roomId);

            return Optional.of(new Message(resultSet.getLong(1), user, chatroom,
                    resultSet.getString(4), resultSet.getTimestamp(5).toLocalDateTime()));
        } catch (SQLException e) {
            e.getMessage();
        }
        return Optional.empty();
    }

    private User findUser(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(queryUser + id);
            if (!resultSet.next())
                return null;
            return new User(id, resultSet.getString(2), resultSet.getString(3));
        }
    }

    private Chatroom findChatroom(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(queryChatroom + id);
            if (!resultSet.next())
                return null;
            return new Chatroom(id, resultSet.getString(2));
        }
    }
}

