package com.team7.handsOnJava.model;

public class Product extends BaseModel{
    private String productPrice;
    private String productName;


    public Product(String productID,String productName, String productPrice) {
        super(productID);
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName() {
        this.productName = productName;
    }

    public void setProductPrice() {
        this.productPrice = productPrice;
    }
}

