/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.service.order;

import com.someone.pizzaservice.domain.customer.Customer;
import com.someone.pizzaservice.domain.order.Order;
import com.someone.pizzaservice.domain.pizza.Pizza;
import java.util.Map;

/**
 *
 * @author Andrii_Kozak1
 */
public interface OrderService {

    Order placeNewOrder(Customer customer, Integer... pizzasID);

    Order proceed(Order order);
    
    Order placeNewOrder(Customer customer, Map<Pizza,Integer> pizzas);
}
