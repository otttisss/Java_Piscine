package edu.school21.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import edu.school21.models.Product;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ProductsRepositoryJdbcImplTest {
    private EmbeddedDatabase dataSource;
    private ProductsRepositoryJdbcImpl productsRepositoryJdbc;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(0L, "Meat", 250L),
            new Product(1L, "Fish", 350L),
            new Product(2L, "Chicken", 150L),
            new Product(3L, "Apple", 50L),
            new Product(4L, "Tomato", 55L),
            new Product(5L, "Onion", 20L),
            new Product(6L, "Milk", 100L)
    );

    final Product EXPECTED_FIND_BY_ID = new Product(3L, "Apple", 50L);
    final Product EXPECTED_UPDATE_PRODUCT = new Product(3L, "Apple Inc", 58L);
    final Product EXPECTED_SAVE_PRODUCT = new Product(7L, "Shrimps", 1000L);

    public static void assertProducts(Product expected, Product actual) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getName(), actual.getName());
        Assertions.assertEquals(expected.getPrice(), actual.getPrice());
    }

    @BeforeEach
    void init() {
        dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql").addScript("data.sql").build();
        productsRepositoryJdbc = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    void findAllTest() throws SQLException {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepositoryJdbc.findAll());
    }

    @Test
    void findByIdTest() throws SQLException {
        assertProducts(productsRepositoryJdbc.findById(3L).get(), EXPECTED_FIND_BY_ID);
    }

    @Test
    void updateTest() throws SQLException {
        productsRepositoryJdbc.update(EXPECTED_UPDATE_PRODUCT);
    }

    @Test
    void saveTest() throws SQLException {
        productsRepositoryJdbc.save(EXPECTED_SAVE_PRODUCT);
        Assertions.assertEquals(EXPECTED_SAVE_PRODUCT.getId(), productsRepositoryJdbc.findById(7L).get().getId());
    }

    @Test
    void deleteTest() throws SQLException {
        productsRepositoryJdbc.delete(2L);
        Assertions.assertFalse(productsRepositoryJdbc.findById(2L).isPresent());
    }

    @AfterEach
    void close() {
        dataSource.shutdown();
    }
}
