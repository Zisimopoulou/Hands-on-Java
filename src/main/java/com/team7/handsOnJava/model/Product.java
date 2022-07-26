package com.team7.handsOnJava.model;

import java.math.BigDecimal;

public class Product extends BaseModel{
    private BigDecimal productPrice;
    private String productName;


    public Product(String productID,String productName, BigDecimal productPrice) {
        super(productID);
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {this.productName = productName;}

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }
}

