package com.team7.handsOnJava.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class CustomerPaymentMethod extends BaseModel{
    private BigDecimal discount;
    private CreditDebitCard creditDebitCard;
    private WireTransfer wireTransfer;
    private Cash cash;
    public CustomerPaymentMethod(String paymentMethodID, BigDecimal discount) {
        super(paymentMethodID);
        this.creditDebitCard= creditDebitCard;
        this.wireTransfer= wireTransfer;
        this.cash= cash;
        this.discount= discount;
    }
}
