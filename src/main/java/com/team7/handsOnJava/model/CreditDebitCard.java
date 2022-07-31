package com.team7.handsOnJava.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreditDebitCard{
    private String cardNo, cardDate, cardCvv;
    private BigDecimal discount;
    public CreditDebitCard(String cardNo, String cardDate, String cardCvv) {
        this.discount= BigDecimal.valueOf(0.15);
        this.cardNo= cardNo;
        this.cardDate= cardDate;
        this.cardCvv= cardCvv;
    }
}
