package com.team7.handsOnJava.repository;

import com.team7.handsOnJava.exception.EshopException;

import java.util.List;
import java.util.Optional;

public class ProductRepository implements CRUDRepository<Product, String>{
    @Override
    public List<Product> findAll() throws EshopException {
        return null;
    }

    @Override
    public Optional<Product> findByID(String aString) throws EshopException {
        return Optional.empty();
    }

    @Override
    public boolean delete(Product product) throws EshopException {
        return false;
    }

    @Override
    public Product create(Product product) throws EshopException {
        return null;
    }

    @Override
    public List<Product> createAll(Product... products) throws EshopException {
        return null;
    }

    @Override
    public boolean update(Product product) throws EshopException {
        return false;
    }
}
