package com.team7.handsOnJava.service;

import com.team7.handsOnJava.model.BaseModel;
import com.team7.handsOnJava.model.Product;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.*;
import com.team7.handsOnJava.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService extends BaseService<Product> {
     Optional<Product> findByID(String s) throws EshopException;
     boolean update(Product product) throws EshopException;
}
