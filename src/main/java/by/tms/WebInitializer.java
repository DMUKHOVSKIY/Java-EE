package by.tms;

import by.tms.controller.UserController;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//ApplicationContext - это главный интерфейс в Spring-приложении, который предоставляет информацию о конфигурации приложения.
//Так как это Web, метода main нет(мы не можем создать ApplicationContext), а нам нужен контейнер:
//Диспатчер сервлет - обычный сервлет, внутри которого есть Application context
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer { //надо подключать дополнительно servlet библиотеку так как внутри работают сервлеты
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    } //рутовый апликэйшн контекст/рутовая конфигурация

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfiguration.class};
    }//сюда надо передать конфигурацию, где определены бины наших веб компонентов (контроллеров, интерсепторов)

    //Тут мы указываем рутовый путь нашего сервлета
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; //Если мы укажем "/bye", то надо будет прописывать путь к диспатчер сервлету: localhost:8080/bye/hello/test, а не localhost:8080/hello/test
    }
}
