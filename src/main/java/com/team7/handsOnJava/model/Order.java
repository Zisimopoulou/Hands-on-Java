package com.team7.handsOnJava.model;

import com.team7.handsOnJava.model.Customer;

import java.sql.Date;

public class Order extends BaseModel{
    private Customer customer;
    private String status;

    private String chosenPaymentMethod;

    public Order(String orderID, String status, Customer customer,String chosenPaymentMethod) {
        super(orderID);
        this.customer = customer;
        this.status = status;
        this.chosenPaymentMethod = chosenPaymentMethod;
    }

    public Customer getCustomer() {return customer;}

    public String getChosenPaymentMethod() {return chosenPaymentMethod;}

    public void setChosenPaymentMethod(String chosenPaymentMethod) {this.chosenPaymentMethod = chosenPaymentMethod;}
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
