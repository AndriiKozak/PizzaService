//TASK: реализовать инит метод - вызывать метод инит. 
// cоздать анотацию бенчмарк. - савить над методом, в случае если она присутствует 
// над методом - с параметром (active) либо тру, либо фалс. по умолчанию тру.
// узнать время выполения и вывести в консоль. Написать прокси. 1 - из библиотек 
// для байткода. класс proxy.newProxyInstance; 
// внутри methodHandler есть параметр invoke который срабатывает. 
package com.someone.pizzaservice.infrastructure;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author akozak
 */
public class JavaConfigApplicationContext implements ApplicationContext {

    //to refactor everything into methods. 
    private final Config config = new JavaConfig();
    private Map<String, Object> context = new HashMap<>();

    @Override
    public Object getBean(String name) throws Exception {
        Object bean = alreadyCreated(name);
        if (bean != null) {
            return bean;
        }

        Class clazz = config.getImpl(name);
        if (clazz == null) {
            throw new RuntimeException("bean not found :" + name);
        }

        BeanBuilder builder = new BeanBuilder(clazz);
        builder.crateBean();
        builder.createBeanProxy();
        builder.callPostConstructMethod();
        builder.callInitMethod();
        bean = builder.build();
        context.put(name, bean);
        return bean;

    }

    private Object alreadyCreated(String name) {
        if (context.containsKey(name)) {
            return context.get(name);
        } else {
            return null;
        }
    }

    private class BeanBuilder {

        private final Class<?> clazz;
        private Object bean;
        private Object proxy;

        public BeanBuilder(Class<?> clazz) {
            this.clazz = clazz;
        }

        private String getBeanNameByType(Class<?> paramType) {
            String paramTypeName = paramType.getSimpleName();
            return firstLetterToLowerCase(paramTypeName);
        }

        private String firstLetterToLowerCase(String string) {
            return string.toLowerCase().charAt(0) + string.substring(1);
        }

        public void crateBean() throws Exception {
            Constructor constructor = clazz.getConstructors()[0];
            Class<?>[] paramtypes = constructor.getParameterTypes();
            Object[] paramBeans = new Object[paramtypes.length];
            int i = 0;
            for (Class<?> paramtype : paramtypes) {
                paramBeans[i] = getBean(getBeanNameByType(paramtype));
                i++;
            }
            bean = constructor.newInstance(paramBeans);
        }

        public void createBeanProxy() {
            Set<String> setOfBenchmarkedMethods = new HashSet<>();
            Method[] methods = clazz.getMethods();
            for (Method m : methods) {
                if (m.getAnnotation(Benchmark.class) != null && m.getAnnotation(Benchmark.class).active()) {
                    setOfBenchmarkedMethods.add(m.getName());
                }
            }
            if (setOfBenchmarkedMethods.isEmpty()) {
                return;
            }
            Class<?>[] interfaces = clazz.getInterfaces();
            InvocationHandler handler = new BenchmarkInvocationHandler(bean, setOfBenchmarkedMethods);
            proxy = Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, handler);
        }

        public void callPostConstructMethod() throws Exception {
            Method[] methods = clazz.getMethods();
            for (Method m : methods) {
                if (m.getAnnotation(PostConstruction.class) != null) {
                    m.invoke(bean);
                }
            }
        }

        ;
    
    public void callInitMethod() {
            try {
                Method m = clazz.getMethod("init");
                m.invoke(bean);
            } catch (Exception e) {
            } // It is quite possible for class not to has an init method. Nothing special to do in this case.

        }

        public Object build() {
            return (proxy == null) ? bean : proxy;
        }
    }

}
