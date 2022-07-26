package com.team7.handsOnJava.model;

import com.team7.handsOnJava.model.Customer;

public class Order extends BaseModel{

    private String orderDate, shipmentDate;
    private Customer customer;
    private String status;

    public Order(String orderID, String status, Customer customer, String orderDate, String shipmentDate) {
        super(orderID);
        this.customer = customer;
        this.status = status;
        this.orderDate = orderDate;
        this.shipmentDate = shipmentDate;
    }

    public String getOrderDate() {return orderDate;}
    public String getShipmentDate() {return shipmentDate;}
    public void setOrderDate(String orderDate) {this.orderDate = orderDate;}
    public void setShipmentDate(String shipmentDate) {this.shipmentDate = shipmentDate;}
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
