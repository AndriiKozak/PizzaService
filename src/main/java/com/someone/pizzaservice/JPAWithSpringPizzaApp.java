/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice;

import com.someone.pizzaservice.domain.customer.Address;
import com.someone.pizzaservice.domain.customer.Customer;
import com.someone.pizzaservice.domain.discountcard.StandartDCard;
import com.someone.pizzaservice.domain.order.Order;
import com.someone.pizzaservice.domain.pizza.Pizza;
import com.someone.pizzaservice.domain.pizza.PizzaType;
import com.someone.pizzaservice.repository.order.OrderRepository;
import com.someone.pizzaservice.repository.pizza.PizzaRepository;
import com.someone.pizzaservice.service.order.OrderService;
import com.someone.pizzaservice.service.order.SimpleOrderService;
import java.util.Arrays;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Andrii_Kozak1
 */
public class JPAWithSpringPizzaApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext repoContext = new ClassPathXmlApplicationContext(new String[]{"repositoryMySQLContext.xml"});
        ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}, repoContext);
        PizzaRepository pizzaRepository = (PizzaRepository) repoContext.getBean("SpringJDBCPizzaRepository");
        OrderRepository orderRepository = (OrderRepository) repoContext.getBean("SpringJDBCOrderRepository");
        int id1 = pizzaRepository.createPizza(new Pizza("Pizza1", 12.4, PizzaType.Meat)).getId();
        int id2 = pizzaRepository.createPizza(new Pizza("Pizza2", 24.4, PizzaType.Vegeterian)).getId();
        int id3 = pizzaRepository.createPizza(new Pizza("Pizza3", 22.5, PizzaType.Sea)).getId();

        OrderService orderService = (OrderService) appContext.getBean("orderService");
        Customer customer = new Customer();
        customer.setName("Man from JPASpring Pizza App");
        Address address = new Address("JPASpring Pizza App");
        StandartDCard dCard = new StandartDCard();
        dCard.setTotal(1000.0);
        customer.setDCard(dCard);
        customer.setAddress(Arrays.asList(address));
        Order order = orderService.placeNewOrder(customer, id1, id2, id3);
        order = orderService.placeNewOrder(customer, id1, id2, id3);
        System.out.println(order);

    }

}
