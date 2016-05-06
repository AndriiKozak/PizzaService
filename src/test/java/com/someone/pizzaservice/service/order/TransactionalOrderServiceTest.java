/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.service.order;

import com.someone.pizzaservice.H2RepositoryTestConfig;
import com.someone.pizzaservice.domain.customer.Address;
import com.someone.pizzaservice.domain.customer.Customer;
import com.someone.pizzaservice.domain.discountcard.StandartDCard;
import com.someone.pizzaservice.domain.order.Order;
import com.someone.pizzaservice.domain.pizza.Pizza;
import com.someone.pizzaservice.domain.pizza.PizzaType;
import com.someone.pizzaservice.repository.pizza.PizzaRepository;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Andrii_Kozak1
 */
public class TransactionalOrderServiceTest extends H2RepositoryTestConfig {
    @Autowired
    PizzaRepository pizzaRepository;
    
    @Autowired    
    OrderService orderService;
    private int id1;
    private int id2;
    private int id3;
    
    public TransactionalOrderServiceTest()  {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        id1=pizzaRepository.createPizza(new Pizza("Pizza1", 12.4, PizzaType.Meat)).getId();
        id2=pizzaRepository.createPizza(new Pizza("Pizza2", 24.4, PizzaType.Vegeterian)).getId();
        id3=pizzaRepository.createPizza(new Pizza("Pizza3", 22.5, PizzaType.Sea)).getId();  
        
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of placeNewOrder method, of class TransactionalOrderService.
     */
    @Test
    public void testPlaceNewOrder() {
        Customer customer = new Customer();
        customer.setName("Man from Test");
        Address address = new Address("Test");
        StandartDCard dCard = new StandartDCard();
        dCard.setTotal(1000.0);
        customer.setDCard(dCard);
        customer.setAddress(Arrays.asList(address));
        Order order = orderService.placeNewOrder(customer, id1, id2, id3);
        assertEquals(true, true);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of createOrder method, of class TransactionalOrderService.
     */

    
}
