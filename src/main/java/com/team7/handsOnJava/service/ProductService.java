package com.team7.handsOnJava.service;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.Product;
import com.team7.handsOnJava.repository.CRUDRepository;

import java.util.List;
import java.util.Optional;

import com.team7.handsOnJava.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.Product;
import com.team7.handsOnJava.repository.CRUDRepository;

import java.util.List;
import java.util.Optional;
@Slf4j
public class ProductService implements CRUDRepository<Product, String> {
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() throws EshopException {
        log.debug("Finding all Product.");
        List<Product> productFromDatabase = productRepository.findAll();
        return productFromDatabase;
    }

    @Override
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
            // if it exists, then also go and update its units
            if (isUpdated) {
                // find existing database units and compare with new ones that need to be saved
                String id = product.getProductId();
                Optional<Product> previouslyPersistedUnits = findByID(id);

            }
        }
        return false;
    }
    @Override
    public void delete (Product product) throws EshopException {
        log.debug("Deleting product {}.", product);
        productRepository.delete(product);
    }

            @Override
            public Product create (Product product) throws EshopException {
                log.debug("Creating product {}.", product);
                productRepository.create(product);
                throw new EshopException("Order rejected."); //kapos kati na girizei gia na ginei to status rejected
            }



    @Override
            public List<Product> createAll (Product...products) throws EshopException {
                productRepository.createAll(products);
                return null;
            }

        }