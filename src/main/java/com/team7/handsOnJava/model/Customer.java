package com.team7.handsOnJava.model;

import lombok.Getter;
import lombok.Setter;
import java.lang.String;

@Getter
@Setter
public class Customer extends BaseModel {
    private String customerName, customerEmail, status;
    private CustomerPaymentMethod customerPaymentMethod;
    private CustomerAddress customerAddress;
    private TypeOfCustomer typeOfCustomer;

    public Customer(String customerID, String customerName, String customerEmail,
                    CustomerAddress customerAddress, CustomerPaymentMethod customerPaymentMethod,
                    TypeOfCustomer typeOfCustomer) {
        super(customerID);
        this.customerName= customerName;
        this.customerEmail= customerEmail;
        this.customerAddress= customerAddress;
        this.customerPaymentMethod= customerPaymentMethod;
        this.typeOfCustomer= typeOfCustomer;
    }
    public void setStatus(String status) {this.status = status;}

}
