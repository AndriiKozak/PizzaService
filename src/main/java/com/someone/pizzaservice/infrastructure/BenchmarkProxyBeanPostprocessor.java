/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.infrastructure;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author akozak
 */
public class BenchmarkProxyBeanPostprocessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("before " + beanName + " " + bean.getClass().getCanonicalName());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Set<String> setOfBenchmarkedMethods = new HashSet<>();
        Method[] methods = bean.getClass().getMethods();
        for (Method m : methods) {
            if (m.getAnnotation(Benchmark.class) != null && m.getAnnotation(Benchmark.class).active()) {
                System.out.println(beanName);
                System.out.println(bean.getClass().getName());
                setOfBenchmarkedMethods.add(m.getName());
            }
        }
        if (setOfBenchmarkedMethods.isEmpty()) {
            return bean;
        }
        // Here comes the tricky staff. If bean is enchanced by spring, getInterfaces no more provides actual interfaces
        // however superclass is an actual class, from which we still can get actual interfaces. Union of arrays provides us with actual 
        // interfaces in both cases.
        Class<?>[] interfaces = (Class<?>[]) ArrayUtils.addAll(bean.getClass().getInterfaces(), bean.getClass().getSuperclass().getInterfaces());
        InvocationHandler handler = new BenchmarkInvocationHandler(bean, setOfBenchmarkedMethods);
        bean = Proxy.newProxyInstance(bean.getClass().getClassLoader(), interfaces, handler);
        return bean;
    }

}
