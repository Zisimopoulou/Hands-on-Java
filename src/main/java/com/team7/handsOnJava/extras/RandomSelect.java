package com.team7.handsOnJava.extras;

import com.team7.handsOnJava.model.Product;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;
@Slf4j
public class RandomSelect {
    public String selectRandomPaymentMethod(){
        log.info("Randomly select payment method");
        String [] arr = {"wireTransfer","creditCard","cash"};
        Random random = new Random();
        int select = random.nextInt(arr.length);
        return arr[select];
    }

    public Product selectRandomProduct(List<Product> products){
        log.info("Randomly select a product");
        Random random = new Random();
        int select = random.nextInt(products.size());
        return products.get(select);
    }

}
