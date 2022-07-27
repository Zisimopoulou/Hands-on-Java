package com.team7.handsOnJava.model;

import com.team7.handsOnJava.model.Customer;

import java.sql.Date;

public class Order extends BaseModel{
    private Date orderDate, shipmentDate;
    private Customer customer;
    private String status;

    private String chosenPaymentMethod;

    public Order(String orderID, String status, Customer customer, Date orderDate, Date shipmentDate, String chosenPaymentMethod) {
        super(orderID);
        this.customer = customer;
        this.status = status;
        this.orderDate = orderDate;
        this.shipmentDate = shipmentDate;
        this.chosenPaymentMethod = chosenPaymentMethod;
    }

    public Date getOrderDate() {return orderDate;}
    public Date getShipmentDate() {return shipmentDate;}

    public Customer getCustomer() {return customer;}

    public String getChosenPaymentMethod() {return chosenPaymentMethod;}

    public void setChosenPaymentMethod(String chosenPaymentMethod) {this.chosenPaymentMethod = chosenPaymentMethod;}

    public void setOrderDate(Date orderDate) {this.orderDate = orderDate;}
    public void setShipmentDate(Date shipmentDate) {this.shipmentDate = shipmentDate;}
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
