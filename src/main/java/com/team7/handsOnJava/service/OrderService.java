package com.team7.handsOnJava.service;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.*;
import com.team7.handsOnJava.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderService extends BaseService<Order> {
    public List<OrderItem> deleteOrderItem(List<OrderItem> orderItems, OrderItem orderItem) throws EshopException;
    /*
        public boolean deleteOrderItemFromDatabase(List<OrderItem> orderItems,OrderItem orderItem) throws EshopException;

     */
    OrderItem createOrderItem(OrderItem orderItem) throws EshopException;
    void checkIfShipped(Order order) throws EshopException;
    Long changeOrderItemQuantity(Order order, OrderItem orderItem, Long quantity) throws EshopException;
    BigDecimal DiscountPaymentMethod(OrderItem orderItem) throws EshopException;
    BigDecimal DiscountTypeOfCustomer(OrderItem orderItem) throws EshopException;
    BigDecimal FinalPriceOfOrderItem(OrderItem orderItem,Product product) throws EshopException;
}
