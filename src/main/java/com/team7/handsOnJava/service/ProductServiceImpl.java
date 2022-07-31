package com.team7.handsOnJava.service;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.Customer;
import com.team7.handsOnJava.model.Product;
import com.team7.handsOnJava.repository.CRUDRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.team7.handsOnJava.repository.CustomerRepository;
import com.team7.handsOnJava.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {
    private final ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {this.productRepository = productRepository;}
    @Override
    public CRUDRepository<Product> getRepository() {return productRepository;}

    @Override
    public List<Product> findAll() {
        log.debug("Finding all products.");
        return super.findAll();
    }
    @Override
    public Product create(Product product) throws EshopException {
        log.debug("Creating product {}.", product);
        return super.create(product);
    }

    @Override
    public List<Product> createAll(List<Product> products) throws EshopException {
        return super.createAll();
    }
    @Override
    public void delete(Product product) throws EshopException {
        log.debug("Deleting product {}.", product);
        super.delete(product);
    }

    @Override
    public void deleteById(String id) throws EshopException {
        super.deleteById(id);
    }

    @Override
    public Optional<Product> findByID(String s) {
        log.debug("Finding product matching id {}.", findByID("ID"));
        Optional<Product> productFromDatabaseOptional = productRepository.findByID(s);
        if (productFromDatabaseOptional.isPresent()) {
            Product productFromDatabase = productFromDatabaseOptional.get();

            productFromDatabase.setProductId(s);
        }
        return productFromDatabaseOptional;
    }

    @Override
    public void update(Product product) throws EshopException {
        productRepository.update(product);
    }

    @Override
    public boolean exists(Product entity) throws EshopException {
        return super.exists(entity);
    }

    @Override
    public List<Product> createAll(Product... products) throws EshopException {
        return super.createAll(products);
    }
}