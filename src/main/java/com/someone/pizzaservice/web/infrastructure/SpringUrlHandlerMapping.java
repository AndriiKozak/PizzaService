/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.web.infrastructure;

import java.util.HashMap;
import java.util.Map;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author Andrii_Kozak1
 */
public class SpringUrlHandlerMapping implements HandlerMapping {

    private Map<String, Controller> mapping = new HashMap<>();
    ApplicationContext webContext;

    public SpringUrlHandlerMapping(ApplicationContext webContext) {
        this.webContext = webContext;
        mapping.put("/hello", new HelloController());
        mapping.put("/getpizza", webContext.getBean(GetPizzaController.class));

    }

    @Override
    public Controller getController(String url) {
        return mapping.get(url);
    }
}
