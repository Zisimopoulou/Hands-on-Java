package com.team7.handsOnJava.model;

import lombok.Getter;
import lombok.Setter;
import java.lang.String;

@Getter
@Setter
public class CustomerAddress {
    String street;
    int number, floor;
    public CustomerAddress(String street, int number, int floor) {
        this.street= street;
        this.number= number;
        this.floor= floor;
    }
}
