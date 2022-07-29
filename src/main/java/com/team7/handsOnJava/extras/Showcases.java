package com.team7.handsOnJava.extras;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.Customer;
import com.team7.handsOnJava.model.Order;
import com.team7.handsOnJava.model.OrderItem;
import com.team7.handsOnJava.model.Product;
import com.team7.handsOnJava.repository.CustomerRepository;
import com.team7.handsOnJava.repository.OrderRepository;
import com.team7.handsOnJava.service.CustomerServiceImpl;
import com.team7.handsOnJava.service.OrderServiceImpl;
import com.team7.handsOnJava.service.ReportingService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
//Βγάζει errors και δεν προλάβαμε να διορθώσουμε τα queries.
@Slf4j
public class Showcases{
    private static final CustomerServiceImpl customerService = new CustomerServiceImpl(new CustomerRepository());
    private static final OrderServiceImpl orderService = new OrderServiceImpl(new OrderRepository());
    private static final ReportingService reportingService = new ReportingService();

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

    public void reportingShowcase(Product product, Customer customer) throws EshopException {
        log.info("------------------Reporting Showcases------------------");

        log.info("------------------Total number and cost of purchases for a particular product------------------");
        Map reportProduct = reportingService.findTotNumAndCostOfPurchasesProduct(product);
        log.info("Product with ID = {} : Total quantity, Cost of Purchases = {}", reportProduct.keySet(), reportProduct.values());

        log.info("------------------Total number and cost of purchases for a particular product------------------");
        Map reportPerCustomer = reportingService.findTotNumAndCostOfPurchasesPerCustomer();
        for (Object i : reportPerCustomer.keySet()) {
            log.info("Customer with ID = {} : Total quantity, Cost of Purchases = {}", reportPerCustomer.keySet(), reportProduct.values());
        }
    }
}

