/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.infrastructure;

import com.someone.pizzaservice.repository.order.InMemOrderRepository;
import com.someone.pizzaservice.repository.pizza.InMemPizzaRepository;
import com.someone.pizzaservice.service.order.SimpleOrderService;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author akozak
 */
public class JavaConfig implements Config {

    private Map<String, Class> beans = new HashMap();

    {
        beans.put("orderRepository", InMemOrderRepository.class);
        beans.put("pizzaRepository", InMemPizzaRepository.class);
        beans.put("orderService", SimpleOrderService.class);
    }

    @Override
    public Class<?> getImpl(String bean) {
        return beans.get(bean);
    }

}
