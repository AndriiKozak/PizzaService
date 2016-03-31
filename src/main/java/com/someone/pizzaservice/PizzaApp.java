/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice;

import com.someone.pizzaservice.domain.customer.Address;
import com.someone.pizzaservice.domain.customer.Customer;
import com.someone.pizzaservice.domain.order.Order;
import com.someone.pizzaservice.service.order.OrderService;
import com.someone.pizzaservice.service.order.SimpleOrderService;

/**
 *
 * @author Andrii_Kozak1
 */
public class PizzaApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Customer customer =
                new Customer("Andrii", new Address("Geroyev Stalingrada 20a, fl 323"));
        Order order;
        OrderService orderService = new SimpleOrderService();
        order = orderService.placeNewOrder(customer, 0, 1, 2);

        System.out.println(order);
    }
}


