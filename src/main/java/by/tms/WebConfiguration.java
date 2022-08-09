package by.tms;

import by.tms.interceptor.HelloInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//Класс регистрации конфигураций
@Configuration
@ComponentScan(basePackages = "by.tms")
@EnableWebMvc //интерсепторы и валидаторы не работают без этого
public class WebConfiguration extends WebMvcConfigurerAdapter { //зачеркнут потому что устаревший, но всё еще работает нормально

    @Autowired //Так как мы пометили наш Interceptor "@Component"
    private HelloInterceptor helloInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(helloInterceptor).addPathPatterns("/hello");//массив урлов, куда мы хотим присоединить Interceptor. (Подключили к методу helloWorld класса HelloWorldController)
        //можно ("/hello", "/hello/test" и тд.), можно (("/hello/*").excludePathPattens("/...") - все пути с одной секцией (hello/...) в принципе, кроме одного("/..."))
        //можно ("/hello/**/" - все пути начиная c hello интерсептор(helloInterceptor) будет перехватывать (hello/.../.../.../...)/сколько угодно секций)
       // registry.addInterceptor(helloInterceptor).addPathPatterns("/hello").order(2);//можем указывать очередность интерсепторов
    }

    @Bean // тот самый  view resolver, который нам требуется для работы с jsp
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }


}
