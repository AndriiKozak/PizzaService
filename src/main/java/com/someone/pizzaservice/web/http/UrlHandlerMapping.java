/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.web.http;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Andrii_Kozak1
 */
public class UrlHandlerMapping implements HandlerMapping {

    private Map<String, Controller> mapping = new HashMap<>();

    {
        mapping.put("/hello", new HelloController());
    }

    @Override
    public Controller getController(String url) {
        return mapping.get(url);
    }

}
