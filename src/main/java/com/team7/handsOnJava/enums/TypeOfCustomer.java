package com.team7.handsOnJava.enums;

import java.math.BigDecimal;

public enum TypeOfCustomer {
    B2bBusiness(BigDecimal.valueOf(0.2)),
    B2cIndividual(BigDecimal.valueOf(0)),
    B2gGovernment(BigDecimal.valueOf(0.5));

    private final BigDecimal discount;
    TypeOfCustomer(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }
}
