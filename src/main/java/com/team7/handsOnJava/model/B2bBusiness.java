package com.team7.handsOnJava.model;

import java.math.BigDecimal;

public class B2bBusiness extends TypeOfCustomer{
    public B2bBusiness(String typeOfCustomerID, BigDecimal discount) {
        super(typeOfCustomerID, BigDecimal.valueOf(0.2));
    }
}
