/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.domain.discountcard;

import com.someone.pizzaservice.domain.order.Order;

/**
 *
 * @author Andrii_Kozak1
 */
public interface DCard {

    double getDiscountedPrice(Order order);

    void addToAccount(Order order);
}
