package com.team7.handsOnJava.model;

import com.team7.handsOnJava.model.Customer;

public class Order {
    private String orderID;
    private Customer customer;

    private String status;

    public Order(String orderID, String status, Customer customer) {
        this.orderID = orderID;
        this.customer = customer;
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getStatus() {return status;}

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setStatus(String status) {this.status = status;}

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
