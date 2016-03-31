/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.repository.order;

import com.someone.pizzaservice.domain.order.Order;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrii_Kozak1
 */
public class InMemOrderRepository implements OrderRepository {

    private List<Order> orders = new ArrayList<>();

    @Override
    public Long saveOrder(Order order) {
        orders.add(order);
        return order.getId();
    }
}
