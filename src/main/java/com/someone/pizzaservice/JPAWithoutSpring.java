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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author akozak
 */
public class JPAWithoutSpring {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        Pizza pizza = new Pizza();
        pizza.setName("Margo");
        pizza.setPrice(120.3);
        pizza.setType(PizzaType.Sea);
        Customer customer = new Customer();
        customer.setName("Man from main");
        Address address = new Address("Main :)");
        StandartDCard dCard = new StandartDCard();
        dCard.setTotal(1000.0);
        customer.setDCard(dCard);
        customer.setAddress(Arrays.asList(address));

        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.persist(pizza);
            Map<Pizza, Integer> pizzaCountMap = new HashMap<>();
            pizzaCountMap.put(pizza, 1);
            Order order = new Order(customer, pizzaCountMap);
            em.persist(order);
            em.getTransaction().commit();
            Pizza p = em.find(Pizza.class, 2L);
            System.out.println(p);
        } finally {
            em.close();
            emf.close();
        };
    }

}
