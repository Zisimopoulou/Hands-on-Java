package com.team7.handsOnJava.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WireTransfer{
    String customerIban, shopIban, transactionComment;
    private BigDecimal discount;
    public WireTransfer(String customerIban, String shopIban, String transactionComment) {
        this.discount= BigDecimal.valueOf(1.0);
        this.customerIban= customerIban;
        this.shopIban= shopIban;
        this.transactionComment= transactionComment;
    }
}
