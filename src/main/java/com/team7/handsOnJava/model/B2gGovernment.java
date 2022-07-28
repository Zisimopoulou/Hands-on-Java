package com.team7.handsOnJava.model;

import java.math.BigDecimal;

public class B2gGovernment extends TypeOfCustomer{
    public B2gGovernment(String typeOfCustomerID) {
        super(typeOfCustomerID, BigDecimal.valueOf(0.5));
    }
}
