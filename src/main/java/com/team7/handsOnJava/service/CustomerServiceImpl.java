package com.team7.handsOnJava.service;

import com.team7.handsOnJava.model.*;

public abstract class CustomerServiceImpl {
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
