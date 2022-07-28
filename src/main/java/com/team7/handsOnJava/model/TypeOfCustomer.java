package com.team7.handsOnJava.model;

import java.math.BigDecimal;

public class TypeOfCustomer extends BaseModel{
    private BigDecimal discount;

    public TypeOfCustomer(String typeOfCustomerID, BigDecimal discount) {
        super(typeOfCustomerID);
        this.discount= discount;
    }
}
