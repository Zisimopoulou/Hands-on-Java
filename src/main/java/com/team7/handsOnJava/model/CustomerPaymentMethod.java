package com.team7.handsOnJava.model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerPaymentMethod {
    private String paymentMethodID;
    BigDecimal paymentAmount;
    public CustomerPaymentMethod(String paymentMethodID, BigDecimal paymentAmount) {
        this.paymentMethodID= paymentMethodID;
        this.paymentAmount= paymentAmount;
    }
}
