/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.repository.order;

import com.someone.pizzaservice.domain.order.Order;
import com.someone.pizzaservice.repository.EMPlaceholder;

/**
 *
 * @author Andrii_Kozak1
 */
public class JPAOrderRepository implements OrderRepository {

    EMPlaceholder emPlaceholder;

    public JPAOrderRepository(EMPlaceholder emPlaceholder) {
        this.emPlaceholder = emPlaceholder;
    }

    @Override
    public Long saveOrder(Order order) {
        emPlaceholder.em.persist(order);
        return order.getId();
    }

}
