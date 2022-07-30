package com.team7.handsOnJava.service;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.*;

import java.util.List;
import java.util.regex.Pattern;

public interface CustomerService extends BaseService<Customer>{
    //It only checks the presence of the @ symbol in the email address.
    //If present, then the validation result returns true, otherwise, the result is false
    static boolean validEmail(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
    void changeEmail(Customer customer, String newEmail);
    void changeAddress(Customer customer,  CustomerAddress address);
    void changeCreditDebitCard(Customer customer,  CreditDebitCard creditDebitCard);
    void changeCash(Customer customer, Cash cash);
    void changeWireTransfer(Customer customer, WireTransfer wireTransfer);
    List<Customer> deleteCustomer(String customerID, List<Customer> customers) throws EshopException;

}
