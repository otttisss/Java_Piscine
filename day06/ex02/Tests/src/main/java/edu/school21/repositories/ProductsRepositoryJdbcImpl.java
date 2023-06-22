package edu.school21.repositories;

import edu.school21.models.Product;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import java.util.LinkedList;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> productList = new LinkedList<>();
        String query = "select * from shop.products";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                productList.add(new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getLong("price")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        String query = "select * from shop.products where id = ";

        try (Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {

            statement.execute(query + id);
            ResultSet resultSet = statement.getResultSet();

            if (resultSet.next()) {
                return Optional.of(new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getLong("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void update(Product product) throws SQLException {
        String query = "update shop.products set name = ?, price = ? where id = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, product.getName());
            statement.setLong(2, product.getPrice());
            statement.setLong(3, product.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Product product) throws SQLException {
        String query = "insert into shop.products (name, price) values (?, ?)";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, product.getName());
            statement.setLong(2, product.getPrice());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String query = "delete from shop.products where id = ";

        try (Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement()) {

            statement.execute(query + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
