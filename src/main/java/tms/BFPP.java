package tms;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

//Bean Factory Post Processor (фиолетовый квадрат на фотке)
public class BFPP implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        BeanDefinition user = configurableListableBeanFactory.getBeanDefinition("user");
        user.setScope("prototype"); //Можно достать все классы с префиксом "user" и изменить их на prototype
    }
}
