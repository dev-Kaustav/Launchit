package com.kaustav.launchit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for obtaining database connections. Connection
 * settings are read from the environment variables DB_URL,
 * DB_USER and DB_PASSWORD.
 */
public final class ConnectionManager {
    private ConnectionManager() {}

    public static Connection getConnection() throws SQLException {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        if (url == null) {
            throw new SQLException("DB_URL environment variable not set");
        }
        return DriverManager.getConnection(url, user, password);
    }
}
