package com.team7.handsOnJava.extras;

import com.team7.handsOnJava.model.Product;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;
@Slf4j
public class RandomSelect {
    public static String selectRandomTypeOfCustomer(){
        log.info("Randomly select type of customer");
        String [] arr = {"wireTransfer","creditCard","cash"};
        Random random = new Random();
        int select = random.nextInt(arr.length);
        return arr[select];
    }

    public static Product selectRandomProduct(List<Product> products){
        log.info("Randomly select a product");
        Random random = new Random();
        int select = random.nextInt(products.size());
        return products.get(select);
    }

}
