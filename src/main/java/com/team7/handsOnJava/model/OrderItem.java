package com.team7.handsOnJava.model;

public class OrderItem extends BaseModel{
    private Order order;
    private Product product;
    private Long quantity, price;

    public OrderItem(String orderItemID, Order order, Product product, Long quantity, Long price) {
        super(orderItemID);
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getQuantity(){
        return quantity;
    }
    public Long getPrice() {return price;}
    public void setPrice(Long price) {this.price = price;}
    public void setOrder(Order order) {
        this.order = order;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
