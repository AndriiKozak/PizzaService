/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.infrastructure;

/**
 *
 * @author akozak
 */
public class ServiceLocator {
    private static ServiceLocator instance;

    private ServiceLocator() {
    }
    public static ServiceLocator getInstance() {
        if (instance==null) instance=new ServiceLocator();
        return instance;
    }
    public Object lookup(String bean){
        Config config=new JavaConfig();
        Class clazz = config.getImpl(bean);
        if (clazz==null) throw new RuntimeException("bean not found "+bean);
        try{
        return clazz.newInstance();}
        catch (Exception e) {throw new RuntimeException();}
    }
}
