package com.team7.handsOnJava.service;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.*;
import com.team7.handsOnJava.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderService extends BaseService<Order> {
    List<OrderItem> deleteOrderItem(List<OrderItem> orderItems,OrderItem orderItem) throws EshopException;
    List<Order> deleteOrderBeforeCheckOut(String orderID, List<Order> orders) throws EshopException;
    List<OrderItem> deleteOrderItembyID(List<OrderItem> orderItems,String orderItemID) throws EshopException;
    boolean isOrderItemOnList(List<OrderItem> orderItems,OrderItem orderItem);
    OrderItem createOrderItem(OrderItem orderItem) throws EshopException;
    void checkIfShipped(Order order) throws EshopException;
    Long IncreaseOrDecreaseItemQuantity(OrderItem orderItem, Long quantity) throws EshopException;
    BigDecimal DiscountPaymentMethod(Order order);
    BigDecimal DiscountTypeOfCustomer(Order order);
    BigDecimal FinalPriceOfOrderItem(Order order,Product product);
}
