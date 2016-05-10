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
import com.someone.pizzaservice.repository.EMPlaceholder;
import com.someone.pizzaservice.repository.order.JPAOrderRepository;
import com.someone.pizzaservice.repository.pizza.JPAPizzaRepository;
import com.someone.pizzaservice.service.order.OrderService;
import com.someone.pizzaservice.service.order.SimpleOrderService;
import java.util.Arrays;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Andrii_Kozak1
 */
public class JPAWithoutSpringPizzaApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        EMPlaceholder emPlaceholder = new EMPlaceholder();
        JPAPizzaRepository pizzaRepository = new JPAPizzaRepository(emPlaceholder);
        emPlaceholder.em = em;
        pizzaRepository.cookPizzas();

        JPAOrderRepository orderRepository = new JPAOrderRepository(emPlaceholder);
        OrderService orderService = new SimpleOrderService(orderRepository, pizzaRepository);
        em.close();
        Customer customer = new Customer();
        customer.setName("Man from JPAWS Pizza App");
        Address address = new Address("JPAWS Pizza App");
        StandartDCard dCard = new StandartDCard();
        dCard.setTotal(1000.0);
        customer.setDCard(dCard);
        customer.setAddress(Arrays.asList(address));
        Order order;
        try {
            emPlaceholder.em = emf.createEntityManager();
            emPlaceholder.em.getTransaction().begin();
            order = orderService.placeNewOrder(customer, 1, 2, 3);
            emPlaceholder.em.getTransaction().commit();
        } finally {
            emPlaceholder.em.close();
            emf.close();
        }
        System.out.println(order);
    }

}
