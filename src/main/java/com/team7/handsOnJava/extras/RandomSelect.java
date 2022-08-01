package com.team7.handsOnJava.extras;

import com.team7.handsOnJava.enums.PaymentMethod;
import com.team7.handsOnJava.model.Product;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;
@Slf4j
public class RandomSelect {
    public PaymentMethod selectRandomPaymentMethod(){
        log.info("Randomly select payment method");
        List<PaymentMethod> paymentMethods = List.of(PaymentMethod.WIRETRANSFER,PaymentMethod.CASH,PaymentMethod.CREDITCARD);
        Random random = new Random();
        int select = random.nextInt(paymentMethods.size());
        return paymentMethods.get(select);
    }

    public Product selectRandomProduct(List<Product> products){
        log.info("Randomly select a product");
        Random random = new Random();
        int select = random.nextInt(products.size());
        return products.get(select);
    }

}
