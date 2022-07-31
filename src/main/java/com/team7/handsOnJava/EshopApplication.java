package com.team7.handsOnJava;

import com.team7.handsOnJava.enums.TablesDropCreate;
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

        //orderShowcase(orders,orderItems,products.get(0));
        //customerShowcase(customers.get(0), customers);
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

//    public static void customerShowcase(Customer customer, List<Customer> customers) {
//        try {
//            log.info("------------------Customer Showcase------------------");
//            Customer customerShowcase = customers.get(0);
//
//            log.info("------------------Create Customer in Database------------------");
//            customerService.create(customer);
//        } catch (EshopException e) {
//            log.error("Unable to complete customer Showcase.", e);
//        }
//    }

//    public void orderShowcase(List<Order> orders, List<OrderItem> orderItems, Product product) {
//        try {
//            log.info("------------------Order and OrderItem Showcase------------------");
//            Order orderShowcase = orders.get(0);
//            OrderItem orderItemShowcase = orderItems.get(1);
//
//            log.info("------------------Change quantity of order item------------------");
//            Long amount = 3L;
//            log.info("Quantity before adding amount = {} is {}", amount, orderItemShowcase.getQuantity());
//            orderItemShowcase.setQuantity(orderService.IncreaseOrDecreaseItemQuantity(orderItemShowcase, 3L));
//            log.info("Quantity after adding amount = {} is {}", amount, orderItemShowcase.getQuantity());
//
//            log.info("------------------Add order item in order = {}------------------", orderShowcase.getId());
//            OrderItem newOrderItem = new OrderItem(String.valueOf(orderItems.size() + 1), orderShowcase, product, 1L, orderService.FinalPriceOfOrderItem(orderShowcase, product));
//            if (!orderService.isOrderItemOnList(orderItems, newOrderItem)) {
//                orderItems.add(newOrderItem);
//                log.info("------------------Item with ID = {}, was added to the list------------------", newOrderItem.getId());
//
//            } else {
//                log.info("Item already on the list.");
//            }
//
//
//            log.info("------------------Delete order item with ID = {}------------------", orderItemShowcase.getId());
//            orderItems = orderService.deleteOrderItembyID(orderItems, orderItemShowcase.getId());
//
//            log.info("------------------Delete order with ID = {}------------------", orderShowcase.getId());
//            orders = orderService.deleteOrderBeforeCheckOut(orderShowcase.getId(), orders);
//
//            log.info("------------------Create Order and Order Item in Database------------------");
//            orders.get(0).setStatus("APPROVED");
//            shipOrder(orders.get(0), orderItems);
//
//        } catch (EshopException e) {
//            log.error("Unable to complete order Showcase.", e);
//        }
//    }
//
//    private static void shipOrder(Order order, List<OrderItem> orderItems) throws EshopException {
//        orderService.create(order);
//        for (OrderItem o : orderItems) {
//            if (o.getOrder().getId() == order.getId()) {
//                orderService.createOrderItem(o);
//            }
//        }
//    }

}
