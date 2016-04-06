/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice;

import com.someone.pizzaservice.repository.pizza.PizzaRepository;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author akozak
 */
public class SpringPizzaApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(
                "resouses/appContext.xml"
        );
        System.out.println(appContext.getApplicationName());
        PizzaRepository pizzaRepository =(PizzaRepository)appContext.getBean("pizzaRepository");
        appContext.close();
    }
    
}
//Читать docs.spring.io 6й раздел до 6.5