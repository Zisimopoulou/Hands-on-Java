package com.team7.handsOnJava.repository;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.Customer;

import java.util.List;
import java.util.Optional;

public class CustomerRepository implements CRUDRepository<Customer, Long>{
    @Override
    public List<Customer> findAll() throws EshopException {
        return null;
    }

    @Override
    public Optional<Customer> findByID(Long aLong) throws EshopException {
        return Optional.empty();
    }

    @Override
    public boolean delete(Customer customer) throws EshopException {
        return false;
    }

    @Override
    public Customer create(Customer customer) throws EshopException {
        return null;
    }

    @Override
    public List<Customer> createAll(Customer... customers) throws EshopException {
        return null;
    }

    @Override
    public boolean update(Customer customer) throws EshopException {
        return false;
    }
}
