package com.team7.handsOnJava;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.*;
import com.team7.handsOnJava.repository.CustomerRepository;
import com.team7.handsOnJava.repository.DataSource;
import com.team7.handsOnJava.repository.OrderRepository;
import com.team7.handsOnJava.repository.SqlCommandRepository;
import com.team7.handsOnJava.service.CustomerServiceImpl;
import com.team7.handsOnJava.service.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.System.exit;


@Slf4j
public class  EshopApplication {

    private static final OrderServiceImpl orderService = new OrderServiceImpl(new OrderRepository());
    //private static final CustomerServiceImpl customerService = new CustomerServiceImpl(new CustomerRepository());
    //private static final ProductServiceImpl productService = new ProductServiceImpl(new ProductRepository());

    public static void main(String[] args) throws EshopException {
        initializeDatabase();

        List<Customer> customers = customerCreation();
        List<Product> products = productCreation();
        List<Order> orders = orderCreation(customers);
        List<OrderItem> orderItems = orderItemCreation(orders,products);

        //orderShowcase(customers);

    }

    private static List<Product> productCreation() {
        Product trampoline = new Product("TrampolineID","Trampoline",new BigDecimal(1000));
        Product mattress = new Product("mattressID", "Mattress",new BigDecimal(500));
        return List.of(trampoline,mattress);
    }

    private static List<Customer> customerCreation() {
        CustomerAddress AlexandraAddress = new CustomerAddress("1", "Dorieon", 10L, 2L);
        CreditDebitCard AlexandraCard = new CreditDebitCard("1111","2222","33");
        WireTransfer AlexandraWireTransfer = new WireTransfer("aaa","bb",null);
        Cash AlexandraCash = new Cash();
        B2bBusiness AlexandraB2bBusiness = new B2bBusiness("Business");

        CustomerPaymentMethod AlexandraPaymentMethod = new CustomerPaymentMethod("1", AlexandraCard, AlexandraWireTransfer, AlexandraCash);
        Customer Alexandra = new Customer("1", "Alex", "zisi@zisi.com", AlexandraAddress, AlexandraPaymentMethod, AlexandraB2bBusiness);
        Customer Helena = new Customer("1", "Alex", "zisi@zisi.com", AlexandraAddress, AlexandraPaymentMethod, AlexandraB2bBusiness);

        return List.of(Alexandra, Helena);
    }
    private static List<Order> orderCreation(List<Customer> customers) throws EshopException {
        List<Order> orders = new ArrayList<>();
        try {
            log.info("------------------Create list of orders for every customer------------------");

            int counter = 0;
            for (int i=0;i<customers.size();i++) {
                for (int j = 0; j<2; j++) {
                    orders.add(new Order(String.valueOf(customers.size() + counter), "Pending", customers.get(i), selectRandomTypeOfCustomer()));
                    counter++;
                }
            }
            return orders;
        } catch (IllegalArgumentException e) {
            throw new EshopException("Unable to create list of orders.", e);
        }
    }

    private static List<OrderItem> orderItemCreation(List<Order> orders,List<Product> products) throws EshopException {
        List<OrderItem> orderItems = new ArrayList<>();
        try {
            log.info("------------------Create list of order items for every order------------------");

            int counter = 0;
            for (int i=0;i<orders.size();i++){
                Product randomProduct = selectRandomProduct(products);
                orderItems.add(new OrderItem(String.valueOf(orders.size()+counter),orders.get(i),randomProduct, (long) (new Random().nextInt(10) + 1),randomProduct.getProductPrice()));
            }
            return orderItems;
        } catch (IllegalArgumentException e) {
            throw new EshopException("Unable to create list of orders.", e);
        }
    }
    private static String selectRandomTypeOfCustomer(){
        log.info("Randomly select type of customer");
        String [] arr = {"wireTransfer","creditCard","cash"};
        Random random = new Random();
        int select = random.nextInt(arr.length);
        return arr[select];
    }

    private static Product selectRandomProduct(List<Product> products){
        log.info("Randomly select a product");
        Random random = new Random();
        int select = random.nextInt(products.size());
        return products.get(select);
    }


    private static void createOrderList(List<Order> orders) throws EshopException {
        try {
            for (Order order : orders) {
                orderService.create(order);
            }
        } catch (EshopException e) {
            throw new EshopException("Unable to add orders to the order list.",e);
        }
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
