/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.repository.order;

import com.someone.pizzaservice.domain.order.Order;
import javax.persistence.EntityManager;

/**
 *
 * @author Andrii_Kozak1
 */
public class JPAOrderRepository implements OrderRepository {

    EntityManager em;

    public JPAOrderRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Long saveOrder(Order order) {
        em.persist(order);
        return order.getId();
    }

}
