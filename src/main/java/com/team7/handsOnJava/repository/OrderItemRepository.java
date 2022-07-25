package com.team7.handsOnJava.repository;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.OrderItem;

import java.util.List;
import java.util.Optional;

public class OrderItemRepository implements CRUDRepository<OrderItem, String> {
    @Override
    public List<OrderItem> findAll() throws EshopException {
        return null;
    }

    @Override
    public Optional<OrderItem> findByID(String s) throws EshopException {
        return Optional.empty();
    }

    @Override
    public boolean delete(OrderItem orderItem) throws EshopException {
        return false;
    }

    @Override
    public OrderItem create(OrderItem orderItem) throws EshopException {
        return null;
    }

    @Override
    public List<OrderItem> createAll(OrderItem... orderItems) throws EshopException {
        return null;
    }

    @Override
    public boolean update(OrderItem orderItem) throws EshopException {
        return false;
    }
}
