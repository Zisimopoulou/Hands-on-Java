package com.team7.handsOnJava.model;

import java.math.BigDecimal;

public class B2cIndividual extends TypeOfCustomer{
    public B2cIndividual(String typeOfCustomerID) {
        super(typeOfCustomerID, BigDecimal.valueOf(0.0));
    }
}