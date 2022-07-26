package com.team7.handsOnJava.services;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.Customer;
import com.team7.handsOnJava.model.Order;
import com.team7.handsOnJava.repository.CRUDRepository;
import com.team7.handsOnJava.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class OrderService implements CRUDRepository<Order, String> {

    private OrderRepository orderRepository;
    @Override
    public List<Order> findAll() throws EshopException {
        return null;
    }

    @Override
    public Optional<Order> findByID(String s) throws EshopException {
        return Optional.empty();
    }

    @Override
    public boolean delete(Order order) throws EshopException {
        return false;
    }

    @Override
    public Order create(Order order) throws EshopException {
        log.info("Creating Order");
        Order createdOrder = orderRepository.create(order);
        createOrUpdateOrderItem(order);
        return createdOrder;
    }

    @Override
    public List<Order> createAll(Order... orders) throws EshopException {
        return null;
    }

    @Override
    public boolean update(Order order) throws EshopException {
        return false;
    }
}
