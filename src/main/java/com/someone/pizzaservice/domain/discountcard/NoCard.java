/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.domain.discountcard;

import com.someone.pizzaservice.domain.order.Order;
import com.someone.pizzaservice.domain.pizza.Pizza;
import com.someone.pizzaservice.infrastructure.Domain;

/**
 *
 * @author Andrii_Kozak1
 */
@Domain
public class NoCard implements DCard {

    // This class do calculate dicouts for customers without dicount card.
    // Idea is to implement common interface of all discounts, even without 
    // an amount to keep. 
    public static final int DISCOUNT_THRESOLD = 4;
    public static final double DISCOUNT = 0.3;
    //Singleton temlate
    private static NoCard instance;

    public NoCard() {
    }

    public static NoCard getInstance() {
        if (instance == null) {
            instance = new NoCard();
        }
        return instance;
    }

    @Override
    public double getDiscountedPrice(Order order) {
        Double res = 0.0;
        int size = 0;
        int count;
        Double maxPrice = 0.0;
        for (Pizza pizza : order.getPizzaCountMap().keySet()) {
            count = order.getPizzaCountMap().get(pizza);
            res += count * pizza.getPrice();
            size += count;
            if (pizza.getPrice() > maxPrice) {
                maxPrice = pizza.getPrice();
            }
        }
        if (size >= DISCOUNT_THRESOLD) {
            res -= maxPrice * DISCOUNT;
        }
        return res;
    }

    @Override
    public void addToAccount(Order order) {
    }

}
