/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.domain.order;

import com.someone.pizzaservice.domain.customer.Customer;
import com.someone.pizzaservice.domain.pizza.Pizza;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;

/**
 *
 * @author Andrii_Kozak1
 */
public class OrderTest {
    
    public OrderTest() {
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

    

    
   

 
    @Test(expected=RuntimeException.class)
    public void testSetState() {
        Customer customer=mock(Customer.class);
        List<Pizza> pizzaList=mock(List.class);
        Order order=new Order(customer,pizzaList);
        order.setState(OrderState.DONE);
    }

    /**
     * Test of getCustomer method, of class Order.
     */
  
}
