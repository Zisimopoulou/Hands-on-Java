package com.team7.handsOnJava.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariConnection {
    // Database URL
    private static final String DB_CONNECTION_URL_FILE_MODE = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private static final String DB_USERNAME = "username";
    private static final String DB_PASSWORD = "password";

    // Hikari created datasource
    private static final HikariDataSource hikariDataSource;

    // we only want the following to happen once at the beginning
    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DB_CONNECTION_URL_FILE_MODE);
        config.setUsername(DB_USERNAME);
        config.setPassword(DB_PASSWORD);

        config.setConnectionTimeout(10000);
        config.setIdleTimeout(60000);
        config.setMaxLifetime(1800000);
        config.setMinimumIdle(2);
        config.setMaximumPoolSize(5);

        config.setAutoCommit(true);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        hikariDataSource = new HikariDataSource(config);
    }

    private HikariConnection() {
    }

    public static Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }
}
