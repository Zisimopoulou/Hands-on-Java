package com.team7.handsOnJava.model;

import lombok.Getter;
import lombok.Setter;
import java.lang.String;

@Getter
@Setter
public class CustomerAddress {
    String street;
    Long number, floor;
    public CustomerAddress(String street, Long number, Long floor) {
        this.street= street;
        this.number= number;
        this.floor= floor;
    }
}
