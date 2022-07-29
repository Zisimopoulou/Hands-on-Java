package com.team7.handsOnJava.extras;

import com.team7.handsOnJava.model.*;
import com.team7.handsOnJava.repository.OrderRepository;
import com.team7.handsOnJava.service.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Slf4j
public class ExamplesCreation {
    private final RandomSelect randomSelect = new RandomSelect();
    private final OrderServiceImpl orderService = new OrderServiceImpl(new OrderRepository());

    public List<Product> productCreation() {
        Product trampoline = new Product("TrampolineID","Trampoline",new BigDecimal(1000));
        Product mattress = new Product("mattressID", "Mattress",new BigDecimal(500));
        return List.of(trampoline,mattress);
    }

    public List<Customer> customerCreation() {
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



}
