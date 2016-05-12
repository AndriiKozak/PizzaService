/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.service.pizza;

import com.someone.pizzaservice.H2RepositoryTestConfig;
import com.someone.pizzaservice.domain.pizza.Pizza;
import com.someone.pizzaservice.domain.pizza.PizzaType;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Andrii_Kozak1
 */
public class DelegatePizzaServiceTest extends H2RepositoryTestConfig {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PizzaService pizzaService;
    private int id4 = 4;

    public DelegatePizzaServiceTest() {
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
     * Test of getPizzaByID method, of class DelegatePizzaService.
     */
    @Test
    public void testGetPizzaByID() {
        String insertPizza = "INSERT INTO pizzas (id, name, price, type) values(?,?,?,?)";
        jdbcTemplate.update(insertPizza, id4, "Pizza1", 12.4, PizzaType.Meat.toString());
        Pizza pizza = pizzaService.getPizzaByID(id4);
        assertEquals(pizza.getName(), "Pizza1");
        assertEquals(pizza.getPrice(), 12.4, 0.00000001);
        assertEquals(pizza.getType(), PizzaType.Meat);
    }

    /**
     * Test of createPizza method, of class DelegatePizzaService.
     */
    @Test
    public void testCreatePizza() {
        int id5 = pizzaService.createPizza(new Pizza("Pizza2", 24.4, PizzaType.Vegeterian)).getId();
        int count = jdbcTemplate.queryForObject(
                "select count(*) from pizzas where id = ?", Integer.class, id5);
        assertEquals(count, 1);
    }

}
