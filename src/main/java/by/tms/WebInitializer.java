package by.tms;

import by.tms.controller.UserController;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//Так как это Web, метода main нет(мы не можем создать ApplicationContext), а нам нужен контейнер:
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfiguration.class};
    }

    //Тут мы указываем рутовый путь нашего сервлета
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; //Если мы укажем "/bye", то надо будет прописывать путь к диспатчер сервлету: localhost:8080/bye/hello/test, а не localhost:8080/hello/test
    }
}
