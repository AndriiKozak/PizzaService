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
import java.util.ArrayList;
import java.util.List;
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

    List<Pizza> pizzas;

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
        pizzas = new ArrayList<>();

        {
            pizzas.add(new Pizza("Pizza1", 12.4, PizzaType.Meat));
            pizzas.add(new Pizza("Pizza2", 24.4, PizzaType.Vegeterian));
            pizzas.add(new Pizza("Pizza3", 22.5, PizzaType.Sea));
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
        when(order.getPizzaList()).thenReturn(pizzas);
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
        when(order.getPizzaList()).thenReturn(pizzas);
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
        when(order.getPizzaList()).thenReturn(pizzas);
        instance.addToAccount(order);
        double expResult = 12.4 + 24.4 + 22.5;
        double result = instance.getTotal();
        assertEquals(expResult, result, 0.0);
    }

}
