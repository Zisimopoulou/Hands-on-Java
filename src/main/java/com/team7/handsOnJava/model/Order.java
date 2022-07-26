package com.team7.handsOnJava.model;

import com.team7.handsOnJava.model.Customer;

public class Order extends BaseModel{
    private Customer customer;
    private String status;

    public Order(String orderID, String status, Customer customer) {
        super(orderID);
        this.customer = customer;
        this.status = status;
    }

    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
