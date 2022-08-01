package com.team7.handsOnJava.model;

import com.team7.handsOnJava.enums.PaymentMethod;
import com.team7.handsOnJava.enums.TypeOfCustomer;
import lombok.Getter;
import lombok.Setter;
import java.lang.String;

@Getter
@Setter
public class Customer extends BaseModel {
    private String customerName, customerEmail;
    private CustomerAddress customerAddress;
    private TypeOfCustomer typeOfCustomer;

    public Customer(String customerID, String customerName, String customerEmail,
                    CustomerAddress customerAddress,
                    TypeOfCustomer typeOfCustomer) {
        super(customerID);
        this.customerName= customerName;
        this.customerEmail= customerEmail;
        this.customerAddress= customerAddress;
        this.typeOfCustomer= typeOfCustomer;
    }

}
