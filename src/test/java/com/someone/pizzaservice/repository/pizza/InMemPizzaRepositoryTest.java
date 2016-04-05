/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.repository.pizza;

import com.someone.pizzaservice.domain.pizza.Pizza;
import com.someone.pizzaservice.domain.pizza.PizzaType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrii_Kozak1
 */
public class InMemPizzaRepositoryTest {

    public InMemPizzaRepositoryTest() {
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
     * Test of getPizzaByID method, of class InMemPizzaRepository.
     */
    @Test
    public void testGetPizzaByID() {
        System.out.println("getPizzaByID");
        int id = 2;
        InMemPizzaRepository instance = new InMemPizzaRepository();
        Pizza result = instance.getPizzaByID(id);

        assertEquals(PizzaType.Sea, result.getType());
        assertEquals(22.5, result.getPrice(), 0.0);
        assertEquals("Pizza3", result.getName());

    }

}
