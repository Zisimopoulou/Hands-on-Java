package com.team7.handsOnJava.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreditDebitCard extends CustomerPaymentMethod{
    private String cardNo, cardDate, cardCvv;
    public CreditDebitCard(String cardNo, String cardDate, String cardCvv) {
        this.cardNo= cardNo;
        this.cardDate= cardDate;
        this.cardCvv= cardCvv;
    }

}
