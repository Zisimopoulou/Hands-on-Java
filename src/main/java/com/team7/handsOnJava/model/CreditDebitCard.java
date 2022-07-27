package com.team7.handsOnJava.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreditDebitCard{
    private String cardNo, cardDate, cardCvv;
    private BigDecimal discount;
    public CreditDebitCard(BigDecimal discount, String cardNo, String cardDate, String cardCvv) {
        this.discount= BigDecimal.valueOf(1.5);
        this.cardNo= cardNo;
        this.cardDate= cardDate;
        this.cardCvv= cardCvv;
    }
}
