/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.service.order;

import com.someone.pizzaservice.domain.customer.Customer;
import com.someone.pizzaservice.domain.discountcard.NoCard;
import com.someone.pizzaservice.domain.order.Order;
import com.someone.pizzaservice.repository.pizza.InMemPizzaRepository;
import com.someone.pizzaservice.repository.pizza.PizzaRepository;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Andrii_Kozak1
 */
public class SimpleOrderServiceTest {
    
    public SimpleOrderServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of placeNewOrder method, of class SimpleOrderService.
     */
    @Test(expected=RuntimeException.class)
    public void testPlaceNewOrderUpperLimit() {
        System.out.println("placeNewOrder");
        Customer customer=mock(Customer.class);
        SimpleOrderService instance = new SimpleOrderService();
        Order result = instance.placeNewOrder(customer, 1,1,1,1,1,1,1,1,1,1,1);
    }
    @Test
    public void testPlaceNewOrder() {
        PizzaRepository pizzaRepository =new InMemPizzaRepository();
        System.out.println("placeNewOrder");
        Customer customer=mock(Customer.class);
        when(customer.getDCard()).thenReturn(NoCard.getInstance());
        SimpleOrderService instance = new SimpleOrderService();
        Order result = instance.placeNewOrder(customer, 1,1,1,1,1,1,1,1,1,1);
        double expTotal=9.7*pizzaRepository.getPizzaByID(1).getPrice();
        double resTotal=result.calculateTotalCost();
        assertEquals(expTotal,resTotal,0.0000001);
    }
}
