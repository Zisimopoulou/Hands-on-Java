package com.team7.handsOnJava.model;

import java.math.BigDecimal;

public class OrderItem extends BaseModel{
    private Order order;
    private Product product;
    private BigDecimal price;
    private Long quantity;

    public OrderItem(String orderItemID, Order order, Product product, Long quantity, BigDecimal price) {
        super(orderItemID);
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getQuantity(){
        return quantity;
    }
    public BigDecimal getPrice() {return price;}

    public Order getOrder() {return order;}
    public Product getProduct() {return product;}
    public void setPrice(BigDecimal price) {this.price = price;}
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
