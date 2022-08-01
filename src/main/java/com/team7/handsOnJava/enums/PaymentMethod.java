package com.team7.handsOnJava.enums;

import java.math.BigDecimal;

public enum PaymentMethod {
    WIRETRANSFER(BigDecimal.valueOf(0.1), "wireTransfer"),
    CASH(BigDecimal.valueOf(0), "cash"),
    CREDITCARD(BigDecimal.valueOf(0.5),"creditCard");
    private final BigDecimal discount;
    private final String payment;
    PaymentMethod(BigDecimal discount, String payment) {
        this.payment = payment;
        this.discount = discount;
    }
    public String getPayment() {
        return payment;
    }
    public BigDecimal getDiscount() {
        return discount;
    }
}
