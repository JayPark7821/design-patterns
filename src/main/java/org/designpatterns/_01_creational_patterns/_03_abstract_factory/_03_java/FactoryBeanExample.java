package org.designpatterns._01_creational_patterns._03_abstract_factory._03_java;

import org.designpatterns._01_creational_patterns._02_factory_method._02_after.Ship;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FactoryBeanExample {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(FactoryBeanConfig.class);
        Ship bean = applicationContext.getBean(Ship.class);
        System.out.println(bean);

        ApplicationContext applicationContext1 = new AnnotationConfigApplicationContext(FactoryBeanConfig.class);
        ShipFactory bean1 = applicationContext1.getBean(ShipFactory.class);
        System.out.println(bean1);
    }
}
