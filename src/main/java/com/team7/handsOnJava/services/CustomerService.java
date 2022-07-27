package com.team7.handsOnJava.services;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.*;
import com.team7.handsOnJava.repository.CRUDRepository;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class CustomerService implements CRUDRepository<Customer, String> {
    @Override
    public List<Customer> findAll() throws EshopException {
        return null;
    }

    @Override
    public Optional<Customer> findByID(String s) throws EshopException {
        return Optional.empty();
    }

    @Override
    public boolean delete(Customer customer) throws EshopException {
        return false;
    }

    @Override
    public Customer create(Customer customer) throws EshopException {
        return null;
    }

    @Override
    public List<Customer> createAll(Customer... customers) throws EshopException {
        return null;
    }

    @Override
    public boolean update(Customer customer) throws EshopException {
        return false;
    }
    //It only checks the presence of the @ symbol in the email address.
    //If present, then the validation result returns true, otherwise, the result is false
    public static boolean validEmail(String emailAddress, String regexPattern) {
            return Pattern.compile(regexPattern)
                    .matcher(emailAddress)
                    .matches();
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

}
