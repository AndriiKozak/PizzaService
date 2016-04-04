/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.service.order;

import com.someone.pizzaservice.domain.customer.Customer;
import com.someone.pizzaservice.domain.order.Order;
import com.someone.pizzaservice.domain.pizza.Pizza;
import com.someone.pizzaservice.infrastructure.ServiceLocator;
import com.someone.pizzaservice.repository.order.OrderRepository;
import com.someone.pizzaservice.repository.pizza.PizzaRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrii_Kozak1
 */
public class SimpleOrderService implements OrderService {

    public static final int MAX_ORDER_SIZE = 10;
    public static final int MIN_ORDER_SIZE = 1;
    ServiceLocator locator=ServiceLocator.getInstance();
    private OrderRepository orderRepository; //= (OrderRepository)locator.lookup("OrderRepository");
    private PizzaRepository pizzaRepository; //= (PizzaRepository)locator.lookup("PizzaRepository");

    public SimpleOrderService(OrderRepository orderRepository, PizzaRepository pizzaRepository) {
        this.pizzaRepository=pizzaRepository;
        this.orderRepository=orderRepository;
    }

    
    @Override
    public Order placeNewOrder(Customer customer, Integer... pizzasID) {
        List<Pizza> pizzas = pizzasByArrOfId(pizzasID);
        if (pizzas.size() > MAX_ORDER_SIZE) {
            throw new RuntimeException("Order size exceed order upper limit");
        }
        if (pizzas.size() < MIN_ORDER_SIZE) {
            throw new RuntimeException("Order is less than minimal limit");
        }
        Order newOrder = createOrder(customer, pizzas);
        orderRepository.saveOrder(newOrder);  // set Entity.Order Id and save Entity.Order to in-memory list
        return newOrder;
    }
    

    private Order createOrder(Customer customer, List<Pizza> pizzas) {
        return new Order(customer, pizzas);
    }

    private List<Pizza> pizzasByArrOfId(Integer[] pizzasID) {
        List<Pizza> pizzas = new ArrayList<>();

        for (Integer id : pizzasID) {
            pizzas.add(pizzaRepository.getPizzaByID(id));  // get Entity.Pizza from predifined in-memory list
        }
        return pizzas;
    }

}
