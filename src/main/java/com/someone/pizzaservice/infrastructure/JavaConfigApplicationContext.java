/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.infrastructure;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author akozak
 */
public class JavaConfigApplicationContext implements ApplicationContext {
    //to refactor everything into methods. 
    private final Config config=new JavaConfig();
    private Map<String,Object> context = new HashMap<>();
    @Override
    public Object getBean(String name) throws Exception {
        if  (context.containsKey(name)) return context.get(name);
        Class clazz = config.getImpl(name);
        if (clazz==null) throw new RuntimeException("bean not found :"+ name );
        Constructor constructor= clazz.getConstructors()[0];
        Class<?>[] paramtypes =constructor.getParameterTypes();
        Object[] paramBeans = new Object[paramtypes.length];
        int i=0;
        for(Class<?> paramtype:paramtypes){ 
            String beanName=paramtype.getSimpleName();
            beanName=beanName.substring(0, 1).toLowerCase()+beanName.substring(1);
            paramBeans[i]=getBean(paramtype.getSimpleName());
            i++;
        }
        Object bean=clazz.newInstance();
        BeanBuilder builder=new BeanBuilder(clazz);
        builder.crateBean();
        builder.createBeanProxy();
        builder.callPostConstructMethod();
        builder.callInitMethod();
        bean=builder.build();
        context.put(name,bean);
       return bean;
    
    }
    
    
    private class BeanBuilder{
    private final Class<?> clazz;
    private Object bean;

    public BeanBuilder(Class<?> clazz) {
        this.clazz = clazz;
    }
    
    private String getBeanNameByType(Class<?> paramType){
        String paramTypeName=paramType.getSimpleName();
        return firstLetterToLowerCase(paramTypeName);
    }
    private String firstLetterToLowerCase(String string){
        return string.toLowerCase().charAt(0)+string.substring(1);
    }
    public void crateBean(){
        
    };
    
    public void createBeanProxy(){};
    
    public void callPostConstructMethod() throws Exception{
        Method[] methods = clazz.getMethods();
        for (Method m:methods){
            if (m.getAnnotation(PostConstruction.class)!=null)
                m.invoke(bean);
            }
    };
    
    public void callInitMethod() throws Exception{
        Method m=clazz.getMethod("init");
        m.invoke(bean);
    };
    
    
    
    public Object build(){
        return bean;
    }
}
}
