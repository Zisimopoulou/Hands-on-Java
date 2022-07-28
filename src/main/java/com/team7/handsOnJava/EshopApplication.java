package com.team7.handsOnJava;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.extras.RandomSelect;
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
public class EshopApplication {

    private static final OrderServiceImpl orderService = new OrderServiceImpl(new OrderRepository());
    //private static final CustomerServiceImpl customerService = new CustomerServiceImpl(new CustomerRepository());
    //private static final ProductServiceImpl productService = new ProductServiceImpl(new ProductRepository());
    private static final RandomSelect randomSelect = new RandomSelect();

    public static void main(String[] args) throws EshopException {
        EshopApplication application = new EshopApplication();
    }

    public EshopApplication() {
        initializeDatabase();

        List<Customer> customers = customerCreation();
        List<Product> products = productCreation();
        List<Order> orders = orderCreation(customers);
        List<OrderItem> orderItems = orderItemCreation(orders,products);

        orderShowcase(orders,orderItems,products.get(0));
    }

    private void orderShowcase(List<Order> orders, List<OrderItem> orderItems,Product product) {
        try {
            log.info("------------------Order and OrderItem Showcase------------------");
            Order orderShowcase = orders.get(0);
            OrderItem orderItemShowcase = orderItems.get(1);

            log.info("------------------Change quantity of order item------------------");
            Long amount = 3L;
            log.info("Quantity before adding amount = {} is {}",amount,orderItemShowcase.getQuantity());
            orderItemShowcase.setQuantity(orderService.IncreaseOrDecreaseItemQuantity(orderItemShowcase,3L));
            log.info("Quantity after adding amount = {} is {}",amount,orderItemShowcase.getQuantity());

            log.info("------------------Add order item in order = {}------------------",orderShowcase.getId());
            OrderItem newOrderItem = new OrderItem(String.valueOf(orderItems.size()+1),orderShowcase,product,1L,orderService.FinalPriceOfOrderItem(orderShowcase,product));
            if(!orderService.isOrderItemOnList(orderItems,newOrderItem)) {
                orderItems.add(newOrderItem);
                log.info("------------------Item with ID = {}, was added to the list------------------", newOrderItem.getId());

            } else {
                log.info("Item already on the list.");
            }

            log.info("------------------Delete order item with ID = {}------------------", orderItemShowcase.getId());
            orderItems = orderService.deleteOrderItembyID(orderItems,orderItemShowcase.getId());

            log.info("------------------Delete order with ID = {}------------------",orderShowcase.getId());
            orders = orderService.deleteOrder(orderShowcase.getId(),orders);

            log.info("------------------Create Order and Order Item in Database------------------");
            orders.get(0).setStatus("APPROVED");
            shipOrder(orders.get(0));


        } catch (EshopException e) {
            log.error("Unable to complete order Showcase.",e);
        }
    }

    private static void shipOrder(Order order) throws EshopException {
        orderService.create(order);
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
    private static List<Order> orderCreation(List<Customer> customers) {
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

    private static List<OrderItem> orderItemCreation(List<Order> orders,List<Product> products) {
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