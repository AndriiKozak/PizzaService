/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.repository.order;

import com.someone.pizzaservice.domain.customer.Customer;
import com.someone.pizzaservice.domain.order.Order;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andrii_Kozak1
 */
@Transactional
@Repository("SpringJDBCOrderRepository")
public class SpringJDBCOrderRepository implements OrderRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public Long saveOrder(Order order) {
        Customer customer = order.getCustomer();
        if (customer.getId() != null) {
            //   customer=em.find(Customer.class, customer.getId());
            customer = em.merge(customer);
            //  customer.getAddresses();
            order.setCustomer(customer);
        }
        em.persist(order);
        return order.getId();
    }

    @Override
    public Order GetOrderById(long id) {
        Order order = em.find(Order.class, id);
        return order;
    }

}
