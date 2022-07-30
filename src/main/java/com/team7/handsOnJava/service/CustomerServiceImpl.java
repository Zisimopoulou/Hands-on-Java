package com.team7.handsOnJava.service;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.*;
import com.team7.handsOnJava.repository.CRUDRepository;
import com.team7.handsOnJava.repository.CustomerRepository;
import com.team7.handsOnJava.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements CustomerService{
    private final CustomerRepository customerRepository;
    public CustomerServiceImpl(CustomerRepository customerRepository) {this.customerRepository = customerRepository;}
    @Override
    public CRUDRepository<Customer> getRepository() {return customerRepository;}

    @Override
    public List<Customer> findAll() {
        log.debug("Finding all orders.");
        return super.findAll();
    }

    public Customer create(Customer customer) throws EshopException {
        return super.create(customer);
    }

    @Override
    public List<Customer> createAll(Customer... customers) throws EshopException {
        return super.createAll();
    }

    @Override
    public void delete(Customer customer) throws EshopException {
        log.info("Deleting customer.");
        try {
            super.delete(customer);
        } catch (EshopException e) {
            throw new EshopException("Unable to delete customer.", e);
        }
    }

    @Override
    public void deleteById(String id) throws EshopException {
        super.deleteById(id);
    }

    public void changeEmail(Customer customer, String newEmail){
        customer.setCustomerEmail(newEmail);
    }
    public void changeAddress(Customer customer,  CustomerAddress address){
        customer.setCustomerAddress(address);
    }
    public void changeCreditDebitCard(Customer customer,  CreditDebitCard creditDebitCard){
        customer.getCustomerPaymentMethod().setCreditDebitCard(creditDebitCard);
    }
    public void changeCash(Customer customer, Cash cash){
        customer.getCustomerPaymentMethod().setCash(cash);
    }
    public void changeWireTransfer(Customer customer, WireTransfer wireTransfer){
        customer.getCustomerPaymentMethod().setWireTransfer(wireTransfer);
    }

    public List<Customer> deleteCustomer(String customerID, List<Customer> customers) throws EshopException {
        log.info("Deleting customer.");
        for (int i=0;i<customers.size();i++) {
            if (customerID == customers.get(i).getId()) {
                customers.remove(i);
                return customers;
            }
        }
        throw new EshopException("Unable to find customer");
    }
}
