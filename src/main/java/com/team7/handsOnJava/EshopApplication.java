package com.team7.handsOnJava;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import static java.lang.System.exit;
public class EshopApplication {
    private static final Logger logger = LoggerFactory.getLogger(EshopApplication.class);
    private static final String DB_CONNECTION_URL_FILE_MODE = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private static final String DB_USERNAME = "alexjava";
    private static final String DB_PASSWORD = "Zisi123";
    private final Properties sqlCommands = new Properties();
    private HikariDataSource hikariDataSource;
    public static void main(String[] args) {
        if (args.length == 0) {
            logger.debug("No arguments passed.");
        }
        var demo = new EshopApplication();
        demo.loadSqlCommands();
        demo.loadDatabaseDriver();
        // Initializing Connection Pooling mechanism
        demo.initializeHikariConnectionPooling();
        demo.dropTable();
        demo.createTable();
        //demo.insertData();
    }
    private void loadSqlCommands() {
        try (InputStream inputStream = EshopApplication.class.getClassLoader().getResourceAsStream(
                "sql.properties")) {
            if (inputStream == null) {
                logger.error("Sorry, unable to find sql.properties, exiting application.");
                // Abnormal exit
                exit(-1);
            }
            //load a properties file from class path, inside static method
            sqlCommands.load(inputStream);
        } catch (IOException ex) {
            logger.error("Sorry, unable to parse sql.properties, exiting application.", ex);
            // Abnormal exit
            exit(-1);
        }
    }
    private void dropTable() {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            int result = statement.executeUpdate(sqlCommands.getProperty("drop.table.orders"));
            logger.info("Drop table command was successful with result {}.", result);
        } catch (SQLException ex) {
            logger.warn("Error while dropping table as it does not probably exist.");
        }
    }
    private void createTable() {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            logger.info("Created table command was successful with result {}.",
                    statement.executeUpdate(sqlCommands.getProperty("create.table.orders")));
        } catch (SQLException ex) {
            logger.error("Error while creating table.", ex);
            exit(-1);
        }
    }

    private void initializeHikariConnectionPooling() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DB_CONNECTION_URL_FILE_MODE);
        config.setUsername(DB_USERNAME);
        config.setPassword(DB_PASSWORD);
        // This property controls the maximum number of milliseconds that a client (that's you) will wait for a
        // connection from the pool.
        // Defaults to 30000ms (30secs).
        config.setConnectionTimeout(10000);
        // This property controls the maximum amount of time that a connection is allowed to sit idle in the pool.
        // This setting only applies when minimumIdle is defined to be less than maximumPoolSize.
        // Defaults to 600000ms (10mins).
        config.setIdleTimeout(60000);
        // This property controls the maximum lifetime of a connection in the pool. An in-use connection will never be
        // retired, only when it is closed will it then be removed.
        // Defaults to 1800000ms (30mins).
        config.setMaxLifetime(1800000);
        // This property controls the minimum number of idle connections that HikariCP tries to maintain in the pool.
        // Defaults to maximumPoolSize value.
        config.setMinimumIdle(2);
        // This property controls the maximum size that the pool is allowed to reach, including both idle and in-use
        // connections.
        // Defaults to 10.
        config.setMaximumPoolSize(5);
        // This property controls the default auto-commit behavior of connections returned from the pool.
        // Defaults to true.
        config.setAutoCommit(true);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        hikariDataSource = new HikariDataSource(config);
    }
    private Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }
    private void loadDatabaseDriver() {
        // Traditional way of loading database driver
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        logger.info("Oracle JDBC driver server has been successfully loaded.");
    }
}
