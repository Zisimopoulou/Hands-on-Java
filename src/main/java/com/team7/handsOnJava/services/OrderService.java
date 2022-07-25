package com.team7.handsOnJava.services;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.Customer;
import com.team7.handsOnJava.model.Order;
import com.team7.handsOnJava.repository.CRUDRepository;

import java.util.List;
import java.util.Optional;

public class OrderService implements CRUDRepository<Order, String> {
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
        return null;
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
