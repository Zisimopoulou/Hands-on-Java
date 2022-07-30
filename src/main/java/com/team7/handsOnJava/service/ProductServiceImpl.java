package com.team7.handsOnJava.service;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.Product;
import com.team7.handsOnJava.repository.CRUDRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.team7.handsOnJava.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() throws EshopException {
        log.debug("Finding all Product.");
        List<Product> productFromDatabase = productRepository.findAll();
        return productFromDatabase;
    }

    public Optional<Product> findByID(String s) throws EshopException {
        log.debug("Finding product matching id {}.", findByID("ID"));
        Optional<Product> productFromDatabaseOptional = productRepository.findByID(s);
        if (productFromDatabaseOptional.isPresent()) {
            Product productFromDatabase = productFromDatabaseOptional.get();

            productFromDatabase.setProductId(s);
        }
        return productFromDatabaseOptional;
    }

    public boolean update(Product product) throws EshopException {
        boolean isUpdated = false;
        if (product.getProductPrice() != null) {
            isUpdated = productRepository.update(product);
            if (isUpdated) {
                String id = product.getProductId();
                Optional<Product> previouslyPersistedUnits = findByID(id);

            }
        }
        return false;
    }

    @Override
    public void delete(Product product) throws EshopException {
        log.debug("Deleting product {}.", product);
        productRepository.delete(product);
    }

    @Override
    public void deleteById(Long id) throws EshopException {

    }

    @Override
    public boolean exists(Product entity) throws EshopException {
        return false;
    }

    @Override
    public Product get(Long id) throws EshopException {
        return null;
    }

    @Override
    public Product create(Product product) throws EshopException {
        log.debug("Creating product {}.", product);
        productRepository.create(product);
        throw new EshopException("Product rejected.");
    }


    @Override
    public List<Product> createAll(Product... products) throws EshopException {
        productRepository.createAll(products);
        return null;
    }

    @Override
    public List<Product> createAll(List<Product> entities) throws EshopException {
        return null;
    }

    @Override
    public List<Product> createAll(String ProductName, BigDecimal ProductPrice) throws EshopException {
        return null;
    }
}