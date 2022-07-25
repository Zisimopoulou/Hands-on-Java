package com.team7.handsOnJava.model;

public class OrderItem {
    private String orderItemID;
    private Order order;
    private Product product;
    private int quantity;

    public OrderItem(String orderItemID, Order order, Product product, int quantity) {
        this.orderItemID = orderItemID;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public String getOrderItemID(){
        return orderItemID;
    }
    public int getQuantity(){
        return quantity;
    }

    public void setOrderItemID(String orderItemID) {
        this.orderItemID = orderItemID;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
