package com.team7.handsOnJava.extras;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.Order;
import com.team7.handsOnJava.model.OrderItem;
import com.team7.handsOnJava.model.Product;
import com.team7.handsOnJava.repository.OrderRepository;
import com.team7.handsOnJava.service.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Showcases {
    private static final OrderServiceImpl orderService = new OrderServiceImpl(new OrderRepository());

    public void orderShowcase(List<Order> orders, List<OrderItem> orderItems, Product product) {
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


}
