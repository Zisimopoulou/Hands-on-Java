package com.team7.handsOnJava.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class CustomerPaymentMethod {
    private String paymentMethodID;
    private BigDecimal discount;
    public CustomerPaymentMethod(String paymentMethodID, BigDecimal discount) {
        this.paymentMethodID= paymentMethodID;
        this.discount= discount;
    }
}
