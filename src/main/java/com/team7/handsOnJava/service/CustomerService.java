package com.team7.handsOnJava.service;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.*;

import java.util.List;
import java.util.regex.Pattern;

public interface CustomerService {
    //It only checks the presence of the @ symbol in the email address.
    //If present, then the validation result returns true, otherwise, the result is false
    public static boolean validEmail(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
    public void changeEmail(Customer customer, String newEmail);
    public void changeAddress(Customer customer,  CustomerAddress address);
    public void changeCreditDebitCard(Customer customer,  CreditDebitCard creditDebitCard);
    public void changeCash(Customer customer, Cash cash);
    public void changeWireTransfer(Customer customer, WireTransfer wireTransfer);
    public Customer create(Customer customer);
    List<Customer> deleteCustomer(String customerID, List<Customer> customers) throws EshopException;

}
