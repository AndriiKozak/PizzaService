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
        Object bean=alreadyCreated(name);
        if (bean!=null) return bean;
        
        Class clazz = config.getImpl(name);
        if (clazz==null) throw new RuntimeException("bean not found :"+ name );
        
       
        BeanBuilder builder=new BeanBuilder(clazz);
        builder.crateBean();
   //     builder.createBeanProxy();
   //     builder.callPostConstructMethod();
   //     builder.callInitMethod();
         bean=builder.build();
        context.put(name,bean);
       return bean;
    
    }
    private Object alreadyCreated(String name){
       if  (context.containsKey(name)) return context.get(name);
       else return null;
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
    public void crateBean() throws Exception{
        Constructor constructor= clazz.getConstructors()[0];
        Class<?>[] paramtypes =constructor.getParameterTypes();
        Object[] paramBeans = new Object[paramtypes.length];
        int i=0;
        for(Class<?> paramtype:paramtypes){ 
            paramBeans[i]=getBean(getBeanNameByType(paramtype));
            i++;
        }
        bean=constructor.newInstance(paramBeans);
    };
    
    public void createBeanProxy(){};
    
    public void callPostConstructMethod() throws Exception{
        Method[] methods = clazz.getMethods();
        for (Method m:methods){
            if (m.getAnnotation(PostConstruction.class)!=null)
                m.invoke(bean);
            }
    };
    
    public void callInitMethod() {
        try{
        Method m=clazz.getMethod("init");
        m.invoke(bean);}
        catch (Exception e){} // It is quite possible for class not to has an init method. Nothing special to do in this case.
     
    };
    
    
    
    public Object build(){
        return bean;
    }
}
}
