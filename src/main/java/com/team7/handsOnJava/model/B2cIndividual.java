package com.team7.handsOnJava.model;

import java.math.BigDecimal;

public class B2cIndividual extends TypeOfCustomer{
    public B2cIndividual(String customerID, String customerName, String customerEmail,
                         CustomerAddress customerAddress, CustomerPaymentMethod customerPaymentMethod,
                         TypeOfCustomer typeOfCustomer, String typeOfCustomerID, BigDecimal discount) {
        super(customerID, customerName, customerEmail, customerAddress, customerPaymentMethod,
                typeOfCustomer, typeOfCustomerID, BigDecimal.valueOf(0.0));
    }
}


//Μπορω να περασω το ποσοστο εκπτωσης κατευθειαν
    //μεσα και απλα να καλεις το ποσοστο μεσω της κλασης