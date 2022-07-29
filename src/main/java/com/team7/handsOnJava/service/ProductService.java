package com.team7.handsOnJava.service;

import com.team7.handsOnJava.model.BaseModel;
import com.team7.handsOnJava.model.Product;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.*;
import com.team7.handsOnJava.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService extends BaseService<Product> {

     List<Product> createAll(String ProductName, BigDecimal ProductPrice) throws EshopException;


}
