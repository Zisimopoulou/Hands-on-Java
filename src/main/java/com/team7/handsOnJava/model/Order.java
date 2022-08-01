package com.team7.handsOnJava.model;

import com.team7.handsOnJava.enums.PaymentMethod;
import com.team7.handsOnJava.model.Customer;

import java.sql.Date;

public class Order extends BaseModel{
    private Customer customer;
    private String status;
    private PaymentMethod paymentMethod;
    public Order(String orderID, String status, Customer customer,PaymentMethod paymentMethod) {
        super(orderID);
        this.customer = customer;
        this.status = status;
        this.paymentMethod = paymentMethod;
    }
    public Customer getCustomer() {return customer;}
    public PaymentMethod getPaymentMethod() {return paymentMethod;}
    public void setPaymentMethod(PaymentMethod paymentMethod) {this.paymentMethod = paymentMethod;}
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
