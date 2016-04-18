/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice;

import com.someone.pizzaservice.domain.pizza.Pizza;
import com.someone.pizzaservice.domain.pizza.PizzaType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author akozak
 */
public class JPAWithoutSpring {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
       EntityManager em=emf.createEntityManager();
       Pizza pizza=new Pizza();
       pizza.setId(2L);
       pizza.setName("Margo");
       pizza.setPrice(120.3);
       pizza.setType(PizzaType.Sea);
       try{
           em.getTransaction().begin();
           //em.persist(pizza);
           em.getTransaction().commit();
           Pizza p= em.find(Pizza.class, 2L);
           System.out.println(p);
       }
       finally{
       em.close();
       emf.close();
       };
    }
    
}
