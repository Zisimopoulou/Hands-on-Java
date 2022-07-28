package com.team7.handsOnJava;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.extras.ExamplesCreation;
import com.team7.handsOnJava.extras.Showcases;
import com.team7.handsOnJava.model.*;
import com.team7.handsOnJava.repository.DataSource;
import com.team7.handsOnJava.repository.SqlCommandRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static java.lang.System.exit;


@Slf4j
public class EshopApplication {

    //private static final CustomerServiceImpl customerService = new CustomerServiceImpl(new CustomerRepository());
    //private static final ProductServiceImpl productService = new ProductServiceImpl(new ProductRepository());
    ExamplesCreation examplesCreation = new ExamplesCreation();
    Showcases showcases = new Showcases();

    public static void main(String[] args) throws EshopException {
        EshopApplication application = new EshopApplication();
    }

    public EshopApplication() {
        initializeDatabase();

        List<Customer> customers = examplesCreation.customerCreation();
        List<Product> products = examplesCreation.productCreation();
        List<Order> orders = examplesCreation.orderCreation(customers);
        List<OrderItem> orderItems = examplesCreation.orderItemCreation(orders,products);

        showcases.orderShowcase(orders,orderItems,products.get(0));
    }

    private static void CreateQueries(String command) {
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement()) {
            log.info("Creating tables successful.");
        } catch (SQLException e) {
            log.error("Unable to create tables.", e);
            exit(-1);
        }
    }

    private static void DropQueries (String command) {
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement()) {
            log.info("Dropping tables successful.");
        } catch (SQLException e) {
            log.error("Unable to drop tables.", e);
            exit(-1);
        }
    }
    public static void initializeDatabase() {
        log.info("Initialize Database.");
        dropTables();
        createTables();
    }
    private static void dropTables() {
        List<String> dropTables = List.of(SqlCommandRepository.get("drop.table.customer"),
                SqlCommandRepository.get("drop.table.order"),
                SqlCommandRepository.get("drop.table.product"),
                SqlCommandRepository.get("drop.table.orderitem"));
        for (int i=0;i<dropTables.size();i++){
            DropQueries(dropTables.get(i));
        }
    }

    private static void createTables() {
        List<String> createTables = List.of(SqlCommandRepository.get("create.table.customer"),
                SqlCommandRepository.get("create.table.order"),
                SqlCommandRepository.get("create.table.product"),
                SqlCommandRepository.get("create.table.orderitem"));
        for (int i=0;i<createTables.size();i++){
            CreateQueries(createTables.get(i));
        }
    }

}