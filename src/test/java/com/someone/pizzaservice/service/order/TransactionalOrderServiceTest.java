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
import com.someone.pizzaservice.domain.order.OrderState;
import com.someone.pizzaservice.domain.pizza.Pizza;
import com.someone.pizzaservice.domain.pizza.PizzaType;
import com.someone.pizzaservice.service.pizza.PizzaService;
import java.util.Arrays;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

/**
 *
 * @author Andrii_Kozak1
 */
@Rollback(true)
public class TransactionalOrderServiceTest extends H2RepositoryTestConfig {

    private JdbcTemplate jdbcTemplate;

    //  @Autowired
    //  PizzaService pizzaService;
    @Autowired
    OrderService orderService;
    private int id1 = 1;
    private int id2 = 2;
    private int id3 = 3;
    Pizza pizza1;

    public TransactionalOrderServiceTest() {
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

        //    id1=pizzaRepository.createPizza(new Pizza("Pizza1", 12.4, PizzaType.Meat)).getId();
        //    id2=pizzaRepository.createPizza(new Pizza("Pizza2", 24.4, PizzaType.Vegeterian)).getId();
        //    id3=pizzaRepository.createPizza(new Pizza("Pizza3", 22.5, PizzaType.Sea)).getId();  
    }

    @After
    public void tearDown() {

    }

    /**
     * Test of placeNewOrder method, of class TransactionalOrderService.
     */
    @Test
    public void testPlaceNewOrder() {
        String insertPizza = "INSERT INTO pizzas (id, name, price, type) values(?,?,?,?)";

        jdbcTemplate.update(insertPizza, id1, "Pizza1", 12.4, PizzaType.Meat.toString());
        jdbcTemplate.update(insertPizza, id2, "Pizza2", 24.4, PizzaType.Vegeterian.toString());
        jdbcTemplate.update(insertPizza, id3, "Pizza3", 22.5, PizzaType.Sea.toString());

        Customer customer = new Customer();
        customer.setName("Man from Test");
        Address address = new Address("Test");
        StandartDCard dCard = new StandartDCard();
        dCard.setTotal(1000.0);
        customer.setDCard(dCard);
        customer.setAddress(Arrays.asList(address));
        Order order = orderService.placeNewOrder(customer, id1, id2, id3);
        assertEquals(customer, order.getCustomer());
        assertEquals(order.getPizzaCountMap().entrySet().size(), 3);
        int orderCount = jdbcTemplate.queryForObject(
                "select count(*) from orders where id = ?", Integer.class, order.getId());
        assertEquals(orderCount, 1);
        int pizzaInOrderCount = jdbcTemplate.queryForObject(
                "select count(*) from pizzas_in_order where order_id = ?", Integer.class, order.getId());
        assertEquals(pizzaInOrderCount, 3);

    }

    @Test
    public void testProceed() {
        Customer customer = new Customer();
        customer.setName("Man from Test");
        Address address = new Address("Test");
        StandartDCard dCard = new StandartDCard();
        dCard.setTotal(1000.0);
        customer.setDCard(dCard);
        customer.setAddress(Arrays.asList(address));
        Order order = orderService.placeNewOrder(customer, id1, id2, id3);
        order = orderService.proceed(order);
        assertEquals(order.getState(), OrderState.IN_PROGRESS);
        int orderCount = jdbcTemplate.queryForObject(
                "select count(*) from orders where id = ? AND state = ?", Integer.class, order.getId(), OrderState.IN_PROGRESS.toString());
        assertEquals(orderCount, 1);
    }
}
