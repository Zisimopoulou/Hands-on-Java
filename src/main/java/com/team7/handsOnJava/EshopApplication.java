package com.team7.handsOnJava;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.extras.ExamplesCreation;
import com.team7.handsOnJava.extras.RandomSelect;
import com.team7.handsOnJava.extras.Showcases;
import com.team7.handsOnJava.model.*;
import com.team7.handsOnJava.repository.DataSource;
import com.team7.handsOnJava.repository.OrderRepository;
import com.team7.handsOnJava.repository.SqlCommandRepository;
import com.team7.handsOnJava.service.OrderServiceImpl;
import com.team7.handsOnJava.service.ReportingService;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.System.exit;


@Slf4j
public class EshopApplication {
    private final RandomSelect randomSelect = new RandomSelect();
    private static final OrderServiceImpl orderService = new OrderServiceImpl(new OrderRepository());
    private static final ReportingService reportingService = new ReportingService();


    //private static final CustomerServiceImpl customerService = new CustomerServiceImpl(new CustomerRepository());
    //private static final ProductServiceImpl productService = new ProductServiceImpl(new ProductRepository());
    ExamplesCreation examplesCreation = new ExamplesCreation();
    Showcases showcases = new Showcases();

    public static void main(String[] args) throws EshopException {
        EshopApplication application = new EshopApplication();
    }

    public EshopApplication() {

        List<Customer> customers = customerCreation();
        List<Product> products = productCreation();
        List<Order> orders = orderCreation(customers);
        List<OrderItem> orderItems = orderItemCreation(orders,products);
        initializeDatabase();
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