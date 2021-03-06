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
import java.util.Arrays;
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

        ConfigurableApplicationContext repoContext = new ClassPathXmlApplicationContext(new String[]{"repoContext.xml"});
        repoContext.getEnvironment().setActiveProfiles("dev");
        repoContext.refresh();
        ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}, repoContext);

        OrderService orderService = appContext.getBean(OrderService.class);

        Customer customer = appContext.getBean(Customer.class);

        customer.setName("Andrii");
        customer.setAddress(Arrays.asList(new Address("Geroyev Stalingrada 20a, fl 323")));
        Order order = orderService.placeNewOrder(customer, 1, 2, 3);
        System.out.println(order);

        appContext.close();
    }

}
