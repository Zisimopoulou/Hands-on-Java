package com.team7.handsOnJava.model;

import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder

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

    public  String getProductId() { return getModelId(); }

    public void setProductId(String productId) {setModelId(productId);}

    public void setProductName(String productName) {this.productName = productName;}

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }
}

