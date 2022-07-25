package com.team7.handsOnJava.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WireTransfer extends CustomerPaymentMethod{
    String customerIban, shopIban, transactionComment;

    public WireTransfer(String paymentMethodID, BigDecimal paymentAmount, BigDecimal discount) {
        super(paymentMethodID, paymentAmount, BigDecimal.valueOf(0.1));
        this.customerIban= customerIban;
        this.shopIban= shopIban;
        this.transactionComment= transactionComment;
    }
}
