/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.service.order;

import com.someone.pizzaservice.domain.customer.Customer;
import com.someone.pizzaservice.domain.order.Order;
import com.someone.pizzaservice.domain.pizza.Pizza;
import com.someone.pizzaservice.infrastructure.Benchmark;
import com.someone.pizzaservice.infrastructure.ServiceLocator;
import com.someone.pizzaservice.repository.order.OrderRepository;
import com.someone.pizzaservice.repository.pizza.PizzaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andrii_Kozak1
 */
@Service("orderService")
public class SimpleOrderService implements OrderService {

    public static final int MAX_ORDER_SIZE = 10;
    public static final int MIN_ORDER_SIZE = 1;
    ServiceLocator locator = ServiceLocator.getInstance();
    private OrderRepository orderRepository; //= (OrderRepository)locator.lookup("OrderRepository");
    private PizzaRepository pizzaRepository; //= (PizzaRepository)locator.lookup("PizzaRepository");

    @Autowired
    public SimpleOrderService(OrderRepository orderRepository, PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    @Benchmark
    public Order placeNewOrder(Customer customer, Integer... pizzasID) {
        List<Pizza> pizzas = pizzasByArrOfId(pizzasID);
        if (pizzas.size() > MAX_ORDER_SIZE) {
            throw new RuntimeException("Order size exceed order upper limit");
        }
        if (pizzas.size() < MIN_ORDER_SIZE) {
            throw new RuntimeException("Order is less than minimal limit");
        }
        Order newOrder = createOrder(); 
        newOrder.setCustomer(customer);
        newOrder.setPizzaList(pizzas);
        orderRepository.saveOrder(newOrder);  // set Entity.Order Id and save Entity.Order to in-memory list
        return newOrder;
    }

    //this method is overrided in OrderServiceBean. Here it realised only for test purposes;
    @Lookup
    protected Order createOrder()
    {
        return new Order(null, null);
    }

    private List<Pizza> pizzasByArrOfId(Integer[] pizzasID) {
        List<Pizza> pizzas = new ArrayList<>();

        for (Integer id : pizzasID) {
            pizzas.add(pizzaRepository.getPizzaByID(id)); 
        }
        return pizzas;
    }

}
