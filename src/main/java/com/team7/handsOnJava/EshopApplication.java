package com.team7.handsOnJava;
import com.team7.handsOnJava.extras.ExamplesCreation;
import com.team7.handsOnJava.extras.Showcases;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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
    private final RandomSelect randomSelect = new RandomSelect();
    private static final OrderServiceImpl orderService = new OrderServiceImpl(new OrderRepository());
    private static final ReportingService reportingService = new ReportingService();
    private static final Logger logger = LoggerFactory.getLogger(EshopApplication.class);
    private static final String DB_CONNECTION_URL_FILE_MODE = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private static final String DB_USERNAME = "alexjava4";
    private static final String DB_PASSWORD = "Zisi123";
    private final Properties sqlCommands = new Properties();
    private HikariDataSource hikariDataSource;
    public static void main(String[] args) {
        EshopApplication application = new EshopApplication();

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

    public EshopApplication() {

        List<Customer> customers = customerCreation();
        List<Product> products = productCreation();
        List<Order> orders = orderCreation(customers);
        List<OrderItem> orderItems = orderItemCreation(orders,products);
        //orderShowcase(orders,orderItems,products.get(0));

    }

    public List<Product> productCreation() {
        Product trampoline = new Product("TrampolineID","Trampoline",new BigDecimal(1000));
        Product mattress = new Product("mattressID", "Mattress",new BigDecimal(500));
        return List.of(trampoline,mattress);
    }

    public List<Customer> customerCreation() {
        CustomerAddress AlexandraAddress = new CustomerAddress("1", "Plapouta", 31L, 3L);
        CreditDebitCard AlexandraCard = new CreditDebitCard("4024007167567261","3/2025","538");
        WireTransfer AlexandraWireTransfer = new WireTransfer("GR9801442425955253818659927","GR8501442972218564578227146","Wired Transfer");
        Cash AlexandraCash = new Cash();
        B2bBusiness AlexandraB2bBusiness = new B2bBusiness("Business");

        CustomerPaymentMethod AlexandraPaymentMethod = new CustomerPaymentMethod("1", AlexandraCard, AlexandraWireTransfer, AlexandraCash);
        Customer Alexandra = new Customer("1", "Alex", "zisi@zisi.com", AlexandraAddress, AlexandraPaymentMethod, AlexandraB2bBusiness);
        Customer Helena = new Customer("1", "Alex", "zisi@zisi.com", AlexandraAddress, AlexandraPaymentMethod, AlexandraB2bBusiness);

        return List.of(Alexandra, Helena);
    }
    public List<Order> orderCreation(List<Customer> customers) {
        List<Order> orders = new ArrayList<>();
        log.info("------------------Create list of orders for every customer------------------");

        int counter = 0;
        for (int i=0;i<customers.size();i++) {
            for (int j = 0; j<2; j++) {
                orders.add(new Order(String.valueOf(customers.size() + counter), "Pending", customers.get(i), randomSelect.selectRandomTypeOfCustomer()));
                counter++;
            }
        }
        return orders;
    }

    public List<OrderItem> orderItemCreation(List<Order> orders,List<Product> products) {
        List<OrderItem> orderItems = new ArrayList<>();

        log.info("------------------Create list of order items for every order------------------");

        int counter = 0;
        for (int i=0;i<orders.size();i++) {
            Product randomProduct = randomSelect.selectRandomProduct(products);
            orderItems.add(new OrderItem(String.valueOf(orders.size() + counter),
                    orders.get(i), randomProduct,
                    (long) (new Random().nextInt(10) + 1),
                    orderService.FinalPriceOfOrderItem(orders.get(i),randomProduct)));
        }
        return orderItems;
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
            int result = statement.executeUpdate(sqlCommands.getProperty("drop.table.customer"));
            logger.info("Drop table command was successful with result {}.", result);
        } catch (SQLException ex) {
            logger.warn("Error while dropping table as it does not probably exist.");
        }
    }
    private void createTable() {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            logger.info("Created table command was successful with result {}.",
                    statement.executeUpdate(sqlCommands.getProperty("create.table.customer")));
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
/*
    public void customerShowcase(Customer customer, List<Customer> customers) {
        try {
            log.info("------------------Customer Showcase------------------");
            Customer customerShowcase = customers.get(0);

            log.info("------------------Delete customer with ID = {}------------------", customerShowcase.getId());
            customers= customerService.deleteCustomer(customerShowcase.getId(), customers);

            log.info("------------------Create Customer in Database------------------");
            customers.get(0).setStatus("APPROVED");
            customerService.create(customer);
        } catch (EshopException e) {
            log.error("Unable to complete customer Showcase.", e);
        }
    }

    public void orderShowcase(List<Order> orders, List<OrderItem> orderItems, Product product) {
        try {
            log.info("------------------Order and OrderItem Showcase------------------");
            Order orderShowcase = orders.get(0);
            OrderItem orderItemShowcase = orderItems.get(1);

            log.info("------------------Change quantity of order item------------------");
            Long amount = 3L;
            log.info("Quantity before adding amount = {} is {}", amount, orderItemShowcase.getQuantity());
            orderItemShowcase.setQuantity(orderService.IncreaseOrDecreaseItemQuantity(orderItemShowcase, 3L));
            log.info("Quantity after adding amount = {} is {}", amount, orderItemShowcase.getQuantity());

            log.info("------------------Add order item in order = {}------------------", orderShowcase.getId());
            OrderItem newOrderItem = new OrderItem(String.valueOf(orderItems.size() + 1), orderShowcase, product, 1L, orderService.FinalPriceOfOrderItem(orderShowcase, product));
            if (!orderService.isOrderItemOnList(orderItems, newOrderItem)) {
                orderItems.add(newOrderItem);
                log.info("------------------Item with ID = {}, was added to the list------------------", newOrderItem.getId());

            } else {
                log.info("Item already on the list.");
            }


            log.info("------------------Delete order item with ID = {}------------------", orderItemShowcase.getId());
            orderItems = orderService.deleteOrderItembyID(orderItems, orderItemShowcase.getId());

            log.info("------------------Delete order with ID = {}------------------", orderShowcase.getId());
            orders = orderService.deleteOrder(orderShowcase.getId(), orders);

            log.info("------------------Create Order and Order Item in Database------------------");
            orders.get(0).setStatus("APPROVED");
            shipOrder(orders.get(0), orderItems);

        } catch (EshopException e) {
            log.error("Unable to complete order Showcase.", e);
        }
    }

    private static void shipOrder(Order order, List<OrderItem> orderItems) throws EshopException {
        orderService.create(order);
        for (OrderItem o : orderItems) {
            if (o.getOrder().getId() == order.getId()) {
                orderService.createOrderItem(o);
            }
        }
    }
    */
}
