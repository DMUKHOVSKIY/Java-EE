package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(RootConfiguration.class);
        Invoker invoker = applicationContext.getBean("invoker", Invoker.class);
        invoker.invoke();
    }
}
