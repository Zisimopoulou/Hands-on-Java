package com.team7.handsOnJava.model;

import lombok.Getter;
import lombok.Setter;
import java.lang.String;

@Getter
@Setter
public class Customer {
    private String customerID, customerName, customerEmail;
    private CustomerPaymentMethod customerPaymentMethod;
    private TypeOfCustomer typeOfCustomer;
    public Customer(String customerID, String customerName, String customerEmail,
                    CustomerAddress customerAddress, CustomerPaymentMethod customerPaymentMethod,
                    TypeOfCustomer typeOfCustomer) {
        this.customerID= customerID;
        this.customerName= customerName;
        this.customerEmail= customerEmail;
        this.customerAddress= customerAddress;
        this.customerPaymentMethod= customerPaymentMethod;
        this.typeOfCustomer= typeOfCustomer;
    }
}
