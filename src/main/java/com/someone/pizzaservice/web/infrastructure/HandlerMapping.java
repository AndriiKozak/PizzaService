/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.web.infrastructure;

/**
 *
 * @author Andrii_Kozak1
 */
public interface HandlerMapping {

    public Controller getController(String url);
}
