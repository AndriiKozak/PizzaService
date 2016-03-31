/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.service.order;

import com.someone.pizzaservice.domain.customer.Customer;
import com.someone.pizzaservice.domain.order.Order;

/**
 *
 * @author Andrii_Kozak1
 */
public interface OrderService {

    Order placeNewOrder(Customer customer, Integer... pizzasID);
}
