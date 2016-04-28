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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    //  @Benchmark
    public Order placeNewOrder(Customer customer, Integer... pizzasID) {
        
        Map<Pizza,Integer> pizzaCountMap = pizzasByArrOfId(pizzasID);
        if (pizzasID.length > MAX_ORDER_SIZE) {
            throw new RuntimeException("Order size exceed order upper limit");
        }
        if (pizzasID.length < MIN_ORDER_SIZE) {
            throw new RuntimeException("Order is less than minimal limit");
        }
        Order newOrder = createOrder();
        newOrder.setCustomer(customer);
        newOrder.setPizzaCountMap(pizzaCountMap);
        orderRepository.saveOrder(newOrder);  // set Entity.Order Id and save Entity.Order to in-memory list
        return newOrder;
    }

    //this method is overrided in OrderServiceBean. Here it realised only for test purposes;
    @Lookup
    protected Order createOrder() {
        return new Order(null, null);
    }

    private Map<Pizza,Integer> pizzasByArrOfId(Integer[] pizzasID) {
        Map<Pizza,Integer> pizzaCountMap = new HashMap<>();
        Pizza pizza; int count;
        for (Integer id : pizzasID) {
            pizza=pizzaRepository.getPizzaByID(id);
            if (pizzaCountMap.containsKey(pizza)){
                count=pizzaCountMap.get(pizza)+1;
                pizzaCountMap.remove(pizza);
                pizzaCountMap.put(pizza,count);
            } else {
                pizzaCountMap.put(pizza, 1);
            }
        }
        return pizzaCountMap;
    }

}
