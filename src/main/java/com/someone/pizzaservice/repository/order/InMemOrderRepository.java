/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.repository.order;

import com.someone.pizzaservice.domain.order.Order;
import com.someone.pizzaservice.infrastructure.Benchmark;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Andrii_Kozak1
 */
//@Repository("orderRepository")
public class InMemOrderRepository implements OrderRepository {

    private List<Order> orders = new ArrayList<>();

    @Override
    public Long saveOrder(Order order) {
        orders.add(order);
        return order.getId();
    }

    @Override
    public Order GetOrderById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
