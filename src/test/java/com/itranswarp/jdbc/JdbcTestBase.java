package com.itranswarp.jdbc;

import com.itranswarp.sunny.io.PropertyResolver;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class JdbcTestBase {

    public static final String CREATE_USER = "CREATE TABLE users (id serial PRIMARY KEY, name VARCHAR(255) NOT NULL, age INTEGER)";
    public static final String CREATE_ADDRESS = "CREATE TABLE addresses (id serial PRIMARY KEY, userId INTEGER NOT NULL, address VARCHAR(255) NOT NULL, zip INTEGER)";

    public static final String INSERT_USER = "INSERT INTO users (name, age) VALUES (?, ?)";
    public static final String INSERT_ADDRESS = "INSERT INTO addresses (userId, address, zip) VALUES (?, ?, ?)";

    public static final String UPDATE_USER = "UPDATE users SET name = ?, age = ? WHERE id = ?";
    public static final String UPDATE_ADDRESS = "UPDATE addresses SET address = ?, zip = ? WHERE id = ?";

    public static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
    public static final String DELETE_ADDRESS_BY_USERID = "DELETE FROM addresses WHERE userId = ?";

    public static final String SELECT_USER = "SELECT id, name, age FROM users WHERE id = ?";
    public static final String SELECT_USER_NAME = "SELECT name FROM users WHERE id = ?";
    public static final String SELECT_USER_AGE = "SELECT age FROM users WHERE id = ?";
    public static final String SELECT_ADDRESS_BY_USERID = "SELECT * FROM addresses WHERE userId = ?";

    @BeforeEach
    public void beforeEach() {
        cleanDb();
    }

    public PropertyResolver createPropertyResolver() {
        var ps = new Properties();
        ps.put("summer.datasource.url", "jdbc:postgresql://127.0.0.1:5432/hero_story");
        ps.put("summer.datasource.username", "postgres");
        ps.put("summer.datasource.password", "159357");
        ps.put("summer.datasource.driver-class-name", "org.postgresql.Driver");
        var pr = new PropertyResolver(ps);
        return pr;
    }

    void cleanDb() {
        Path db = Path.of("test.db").normalize().toAbsolutePath();
        try {
            Files.deleteIfExists(db);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}