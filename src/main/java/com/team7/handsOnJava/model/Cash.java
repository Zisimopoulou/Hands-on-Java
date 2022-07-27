package com.team7.handsOnJava.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Cash{
    private BigDecimal discount;
    public Cash(BigDecimal discount) {
        this.discount= BigDecimal.valueOf(0);
    }
}
