package com.team7.handsOnJava.service;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.Customer;
import com.team7.handsOnJava.model.Product;
import com.team7.handsOnJava.repository.CustomerRepository;
import com.team7.handsOnJava.repository.OrderRepository;
import com.team7.handsOnJava.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Map;
@Slf4j
public class ReportingService {
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;

    public Map<String, ArrayList<String>> findTotNumAndCostOfPurchasesProduct(Product product) throws EshopException {
        log.info("Finding total number and cost of purchases for the product with ID = {}",product.getId());
        return productRepository.findTotNumAndCostOfPurchasesProduct(product);
    }
/*
    public Map<String, ArrayList<String>> findTotNumAndCostOfPurchasesProduct(Customer customer) throws EshopException {
        log.info("Finding total number and cost of purchases for the product with ID = {}",customer.getId());
        return customerRepository.findTotNumAndCostOfPurchasesCustomer(customer);
    }
    */

    public Map<String, ArrayList<String>> findTotNumAndCostOfPurchasesPerCustomer() throws EshopException {
        log.info("Finding total number and cost of purchases per customer.");
        return customerRepository.findTotNumAndCostOfPurchasesPerCustomer();
    }

}