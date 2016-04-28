/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.domain.discountcard;

import com.someone.pizzaservice.domain.customer.Customer;
import com.someone.pizzaservice.domain.order.Order;
import com.someone.pizzaservice.domain.pizza.Pizza;
import com.someone.pizzaservice.domain.pizza.PizzaType;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Andrii_Kozak1
 */
public class StandartDCardTest {

    Map<Pizza,Integer> pizzas;

    public StandartDCardTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        pizzas = new HashMap<Pizza,Integer>();

        {
            pizzas.put(new Pizza(1, "Pizza1test", 12.4, PizzaType.Meat),1);
            pizzas.put(new Pizza(2, "Pizza2test", 24.4, PizzaType.Vegeterian),1);
            pizzas.put(new Pizza(3, "Pizza3test", 22.5, PizzaType.Sea),1);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getDiscountedPrice method, of class StandartDCard.
     */
    @Test
    public void testGetDiscountedPriceBigTotal() {
        System.out.println("getDiscountedPriceBigTotal");
        StandartDCard instance = new StandartDCard();
        instance.setTotal(100500.0);
        Order order = mock(Order.class);
        Customer customer = mock(Customer.class);
        when(order.getPizzaCountMap()).thenReturn(pizzas);
        when(order.getCustomer()).thenReturn(customer);
        when(customer.getDCard()).thenReturn(instance);
        double expResult = 0.7 * (12.4 + 24.4 + 22.5);
        double result = instance.getDiscountedPrice(order);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testGetDiscountedPriceSmallTotal() {
        System.out.println("getDiscountedPriceSmallTotal");
        StandartDCard instance = new StandartDCard();
        instance.setTotal(100);
        Order order = mock(Order.class);
        Customer customer = mock(Customer.class);
        when(order.getPizzaCountMap()).thenReturn(pizzas);
        when(order.getCustomer()).thenReturn(customer);
        when(customer.getDCard()).thenReturn(instance);
        double expResult = 12.4 + 24.4 + 22.5 - 0.1 * 100;
        double result = instance.getDiscountedPrice(order);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of addToAccount method, of class StandartDCard.
     */
    @Test
    public void testAddToAccount() {
        StandartDCard instance = new StandartDCard();
        System.out.println("addToAccount");
        Order order = mock(Order.class);
        when(order.getPizzaCountMap()).thenReturn(pizzas);
        instance.addToAccount(order);
        double expResult = 12.4 + 24.4 + 22.5;
        double result = instance.getTotal();
        assertEquals(expResult, result, 0.0);
    }

}
