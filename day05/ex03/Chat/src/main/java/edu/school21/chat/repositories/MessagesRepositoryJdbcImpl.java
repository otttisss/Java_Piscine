package edu.school21.chat.repositories;

import edu.school21.chat.exception.NotSavedSubEntityException;
import edu.school21.chat.models.*;

import java.sql.*;
import java.time.LocalDateTime;
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
    public void update(Message message) throws NotSavedSubEntityException {
        Timestamp time = null;
        Long userId = message.getAuthor().getId();

        try (Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {

            String messageSelect = "select * from chat.messages where id = ";
            String userSelect = "select * from chat.users where id = ";

            ResultSet resultSet = statement.executeQuery(userSelect + userId);
            if (!resultSet.next())
                throw new NotSavedSubEntityException("User with id = " + userId + "doesn't exist");
            statement.close();
            PreparedStatement preparedStatement = connection.prepareStatement(messageSelect + message.getId());
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                save(message);
                return;
            }

            if (message.getText() == null) {
                message.setText("");
            }
            if (message.getTimestamp() != null)
                time = Timestamp.valueOf(message.getTimestamp());

            String updateQuery = "update chat.messages set author = ?, room = ?, message = ?," +
                    " timestamp = ? where id = ?";
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setLong(1, message.getAuthor().getId());
            preparedStatement.setLong(2, message.getRoom().getId());
            preparedStatement.setString(3, message.getText());
            preparedStatement.setTimestamp(4, time);
            preparedStatement.setLong(5, message.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Message message) throws NotSavedSubEntityException {
        Long userId, roomId;
        String localDateTime = "'null'";

        userId = message.getAuthor().getId();
        roomId = message.getRoom().getId();
        try(Connection con = dataSource.getConnection();
            Statement st = con.createStatement()) {
            String uQuery = "select * from chat.users where id = ";
            ResultSet rs = st.executeQuery(uQuery + userId);

            if (!rs.next())
                throw new NotSavedSubEntityException("User with id = " + userId + " doesn't exist");
            String cQuery = "select * from chat.rooms where id = ";
            rs = st.executeQuery(cQuery + roomId);

            if (!rs.next())
                throw new NotSavedSubEntityException("Chatroom with id = " + roomId + " doesn't exist");

            if (message.getTimestamp() != null)
                localDateTime = "'" + Timestamp.valueOf(message.getTimestamp()) + "'";
            rs = st.executeQuery("insert into chat.messages (author, room, message, timestamp)" +
                    "values (" + userId + ", " + roomId + ", '" + message.getText() + "', " + localDateTime + ") RETURNING id");

            if (!rs.next()) {
                throw new NotSavedSubEntityException("Internal Error");
            }
            message.setId(rs.getLong(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            Timestamp time = resultSet.getTimestamp(5);
            LocalDateTime timeOptional;
            if (time == null)
                timeOptional = LocalDateTime.now();
            else
                timeOptional = resultSet.getTimestamp(5).toLocalDateTime();
            return Optional.of(new Message(resultSet.getLong(1), user, chatroom,
                    resultSet.getString(4), timeOptional));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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

