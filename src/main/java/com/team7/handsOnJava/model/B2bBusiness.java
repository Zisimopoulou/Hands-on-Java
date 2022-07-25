package com.team7.handsOnJava.model;

import java.math.BigDecimal;

public class B2bBusiness extends TypeOfCustomer{
    public B2bBusiness(String customerID, String customerName, String customerEmail,
                         CustomerAddress customerAddress, CustomerPaymentMethod customerPaymentMethod,
                         TypeOfCustomer typeOfCustomer, String typeOfCustomerID, BigDecimal discount) {
        super(customerID, customerName, customerEmail, customerAddress, customerPaymentMethod,
                typeOfCustomer, typeOfCustomerID, BigDecimal.valueOf(0.2));
    }
}
