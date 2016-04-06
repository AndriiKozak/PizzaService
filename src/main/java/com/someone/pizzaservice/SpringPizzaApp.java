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
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Andrii_Kozak1
 */
public class SpringPizzaApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext("appContext.xml");
        OrderService orderService = (OrderService) appContext.getBean("orderService");
        Customer customer
                = new Customer("Andrii", new Address("Geroyev Stalingrada 20a, fl 323"));
        Order order=orderService.placeNewOrder(customer, 0, 1, 2);
        System.out.println(order);
        appContext.close();
    }
    
}
