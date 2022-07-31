package com.team7.handsOnJava;

import com.team7.handsOnJava.enums.TablesDropCreate;
import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.extras.ExamplesCreation;
import com.team7.handsOnJava.extras.Showcases;
import com.team7.handsOnJava.repository.CustomerRepository;
import com.team7.handsOnJava.repository.HikariConnection;
import com.team7.handsOnJava.service.CustomerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import static java.lang.System.exit;
import com.team7.handsOnJava.extras.RandomSelect;
import com.team7.handsOnJava.model.*;
import com.team7.handsOnJava.repository.OrderRepository;
import com.team7.handsOnJava.service.OrderServiceImpl;
import com.team7.handsOnJava.service.ReportingService;

@Slf4j
public class EshopApplication {
    ExamplesCreation examplesCreation = new ExamplesCreation();
    Showcases showcases = new Showcases();
    private static final RandomSelect randomSelect = new RandomSelect();
    private static final OrderServiceImpl orderService = new OrderServiceImpl(new OrderRepository());
    private static final CustomerServiceImpl customerService = new CustomerServiceImpl(new CustomerRepository());
    private static final ReportingService reportingService = new ReportingService();
    private static final Properties sqlCommands = new Properties();

    public static void main(String[] args) {
        EshopApplication eshopApplication = new EshopApplication();
    }

    public EshopApplication() {
        initializeDatabase();

        List<Customer> customers = ExamplesCreation.customerCreation();
        List<Product> products = ExamplesCreation.productCreation();
        List<Order> orders = ExamplesCreation.orderCreation(customers);
        List<OrderItem> orderItems = ExamplesCreation.orderItemCreation(orders, products);

        Showcases.customerShowcase(customers.get(0), customers);
        showcases.orderShowcase(orders,orderItems,products.get(0));

    }

    private static void loadSqlCommands() {
        try (InputStream inputStream = EshopApplication.class.getClassLoader().getResourceAsStream(
                "sql.properties")) {
            if (inputStream == null) {
                log.error("Sorry, unable to find sql.properties, exiting application.");
                // Abnormal exit
                exit(-1);
            }
            sqlCommands.load(inputStream);
        } catch (IOException ex) {
            log.error("Sorry, unable to parse sql.properties, exiting application.", ex);
            // Abnormal exit
            exit(-1);
        }
    }

    public static void initializeDatabase() {
        log.info("--Initializing database tables (if they do not exist)");
        loadSqlCommands();
        loadDatabaseDriver();
        allTablesDropCreate();
    }

    private static void dropTables(String table) {
        try (Connection connection = HikariConnection.getConnection(); Statement statement = connection.createStatement()) {
            int result = statement.executeUpdate(sqlCommands.getProperty(table));
            log.info("Successful drop of tables.");
        } catch (SQLException ex) {
            log.info("Error while dropping tables.");
        }
    }

    private static void allTablesDropCreate() {
        TablesDropCreate.stream()
                .filter(d -> d.getDropOrCreate().equals("drop"))
                .forEach(d -> dropTables(d.getTable()));
        TablesDropCreate.stream()
                .filter(d -> d.getDropOrCreate().equals("create"))
                .forEach(d -> createTables(d.getTable()));
    }

    private static void createTables(String table) {
        try (Connection connection = HikariConnection.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommands.getProperty(table));
            log.info("Successful creation of tables.");
        } catch (SQLException ex) {
            log.error("Error while creating tables.", ex);
            exit(-1);
        }
    }

    private static void loadDatabaseDriver() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        log.info("Oracle JDBC driver server has been successfully loaded.");
    }
}
