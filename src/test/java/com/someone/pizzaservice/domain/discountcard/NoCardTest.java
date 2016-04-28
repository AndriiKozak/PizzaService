/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.domain.discountcard;

import com.someone.pizzaservice.domain.order.Order;
import com.someone.pizzaservice.domain.pizza.Pizza;
import com.someone.pizzaservice.domain.pizza.PizzaType;
import java.util.HashMap;
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
public class NoCardTest {

    HashMap<Pizza,Integer> pizzas;

    public NoCardTest() {
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
     * Test of getDiscountedPrice method, of class NoCard.
     */
    @Test
    public void testGetDiscountedPriceBigOrder() {
        System.out.println("getDiscountedPrice");
        pizzas.put(new Pizza(4, "TestExpensivePizza", 100500.0, PizzaType.Meat),1);
        Order order = mock(Order.class);
        when(order.getPizzaCountMap()).thenReturn(pizzas);
        NoCard instance = NoCard.getInstance();
        double expResult = 12.4 + 24.4 + 22.5 + 0.7 * 100500;
        double result = instance.getDiscountedPrice(order);
        assertEquals(expResult, result, 0.0000001);
    }

    @Test
    public void testGetDiscountedPriceLittleOrder() {
        System.out.println("getDiscountedPrice");
        Order order = mock(Order.class);
        when(order.getPizzaCountMap()).thenReturn(pizzas);
        NoCard instance = NoCard.getInstance();
        double expResult = 12.4 + 24.4 + 22.5;
        double result = instance.getDiscountedPrice(order);
        assertEquals(expResult, result, 0.00000001);
    }

}
