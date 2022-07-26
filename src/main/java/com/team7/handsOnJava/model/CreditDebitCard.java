package com.team7.handsOnJava.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreditDebitCard extends CustomerPaymentMethod{
    private String cardNo, cardDate, cardCvv;

    public CreditDebitCard(String paymentMethodID, BigDecimal discount) {
        super(paymentMethodID, BigDecimal.valueOf(1.5));
        this.cardNo= cardNo;
        this.cardDate= cardDate;
        this.cardCvv= cardCvv;
    }
}
