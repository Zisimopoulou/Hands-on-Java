package com.team7.handsOnJava.model;

import java.math.BigDecimal;

public class TypeOfCustomer extends Customer{
    private String typeOfCustomerID;
    private BigDecimal discount;

    public TypeOfCustomer(String customerID, String customerName, String customerEmail,
                          CustomerAddress customerAddress, CustomerPaymentMethod customerPaymentMethod,
                          TypeOfCustomer typeOfCustomer, String typeOfCustomerID, BigDecimal discount) {
        super(customerID, customerName, customerEmail, customerAddress, customerPaymentMethod, typeOfCustomer);
        this.typeOfCustomerID= typeOfCustomerID;
        this.discount= discount;
    }
}
