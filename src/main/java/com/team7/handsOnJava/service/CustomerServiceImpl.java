package com.team7.handsOnJava.service;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.*;
import com.team7.handsOnJava.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public class CustomerServiceImpl {
    private CustomerRepository customerRepository;
    public CustomerServiceImpl(CustomerRepository customerRepository) {this.customerRepository = customerRepository;}
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
    public Customer create(Customer customer) throws EshopException {
        if (customer.getStatus() == "APPROVED") {
            log.info("Approving customer.");
            return customerRepository.create(customer);
        }
        throw new EshopException("Order rejected.");
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
