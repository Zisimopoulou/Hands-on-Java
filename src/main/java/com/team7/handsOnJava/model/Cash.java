package com.team7.handsOnJava.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Cash extends CustomerPaymentMethod{
    public Cash(String paymentMethodID, BigDecimal paymentAmount, BigDecimal discount) {
        super(paymentMethodID, paymentAmount, BigDecimal.valueOf(0));
    }
}
