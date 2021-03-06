/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice;

import com.someone.pizzaservice.domain.customer.Address;
import com.someone.pizzaservice.domain.customer.Customer;
import com.someone.pizzaservice.domain.order.Order;
import com.someone.pizzaservice.infrastructure.ApplicationContext;
import com.someone.pizzaservice.infrastructure.JavaConfigApplicationContext;
import com.someone.pizzaservice.repository.order.OrderRepository;
import com.someone.pizzaservice.repository.pizza.PizzaRepository;
import com.someone.pizzaservice.service.order.OrderService;
import java.util.Arrays;

/**
 *
 * @author Andrii_Kozak1
 */
public class PizzaApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        ApplicationContext ac = new JavaConfigApplicationContext();

        OrderService orderService = (OrderService) ac.getBean("orderService");

        Customer customer
                = new Customer("Andrii", Arrays.asList(new Address("Geroyev Stalingrada 20a, fl 323")));
        Order order;

        order = orderService.placeNewOrder(customer, 0, 1, 2);
        System.out.println(order);
    }
}
