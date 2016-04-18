/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.infrastructure;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Set;

/**
 *
 * @author Andrii_Kozak1
 */
public class BenchmarkInvocationHandler implements InvocationHandler {

    private Object obj;
    private Set<String> setOfBenchmarkedMethods;

    public BenchmarkInvocationHandler(Object obj, Set<String> setOfBenchmarkedMethods) {
        this.setOfBenchmarkedMethods = setOfBenchmarkedMethods;
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        Object result;
        long nanoTime;
        if (setOfBenchmarkedMethods.contains(method.getName())) {
            nanoTime = -System.nanoTime();
            result = method.invoke(obj, args);
            nanoTime += System.nanoTime();
            System.out.println("Execution of " + method.getName() + " takes " + nanoTime + " nanoseconds");
        } else {
            result = method.invoke(obj, args);
        }

        return result;
    }

}
