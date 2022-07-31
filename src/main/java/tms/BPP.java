package tms;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

//BeanPostProcessor - 2 точки, через которые проходят все объекты, когда они создаются контейнером
//Просто ещё одна возможность кастомизации до того, как бины будут использованы

@Component
public class BPP implements BeanPostProcessor {//собственный бин пост процессор

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Before " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("After " + beanName);
        return bean;
    }
}
