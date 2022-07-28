package com.team7.handsOnJava;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.*;
import com.team7.handsOnJava.repository.OrderRepository;
import com.team7.handsOnJava.service.CustomerServiceImpl;
import com.team7.handsOnJava.service.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class  EshopApplication {

    private static final OrderServiceImpl orderService = new OrderServiceImpl(new OrderRepository());
    private static final CustomerServiceImpl customerService = new CustomerServiceImpl(new CustomerRepository());
    private static final ProductServiceImpl productService = new ProductServiceImpl(new ProductRepository());

    public static void main(String[] args) {


        // orderShowcase(customers);
        // customerShowcase(customers);
        // productShowcase(customers);
        // reportShowcase(customers);

    }
}

