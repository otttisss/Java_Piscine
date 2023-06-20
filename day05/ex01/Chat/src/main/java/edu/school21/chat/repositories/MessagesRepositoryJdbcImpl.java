package edu.school21.chat.repositories;
import edu.school21.chat.models.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import javax.sql.DataSource;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final DataSource dataSource;
    private final String query = "select * from chat.messages where id = ";

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query + id);

            if (resultSet.next()) {
                return Optional.of(new Message(resultSet.getInt("id"),
                        new User(resultSet.getInt("author"), resultSet.getString(2), resultSet.getString(3)),
                        new Chatroom(resultSet.getInt("room_name"), resultSet.getString(2)),
                        resultSet.getString("message"), resultSet.getTimestamp("time").toLocalDateTime()));
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return Optional.empty();
    }
}
