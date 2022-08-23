package by.tms;

import by.tms.interceptor.HelloInterceptor;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

//Класс регистрации конфигураций
@Configuration
@ComponentScan(basePackages = "by.tms")
@EnableWebMvc //интерсепторы и валидаторы не работают без этого
@EnableTransactionManagement
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
 //-------------------------Подключаем
    //У гибернейта есть 2 аспекта конфигураций. Тут прописан первый
    //Если мы захотим сделать это в дажва SE, то все, что мы прописываем через @Bean надо будет прописывать через xml-конфигурации
    //2 аспект конфигурации - это маппинг. Мы должны пометить поля наших классов(что они из себя представляют). Что будет являться id, какое поле будет уникальным и тд.
    //Но если это Java SE, то это делалось во 2 файле конфигурации
    //Следовательно раньше, чтобы раньше работать с гибернейтом надо было писать 2 xml конфигурации. 1 - настройки гибернейта, 2 - маппинг объекта (что с чем связано)
    @Bean //Этот тип находится в Spring ORM. Внутри его гибернейт кор (мы не работаем с кором напрямую) Этот бин и будет основным, который все решает по поводу работы гибернейт в спринге
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("by.tms.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean //Это интерфейс из JDK.(по факту чисто jdbc) Его имплементируют Connection pull-ы. Это бин не касается гибернейтп
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource(); //не классическая реализация DataSource(), а BasicDataSource() - из пулла коннекшена
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("123456");
        return dataSource;
    }

    @Bean //Это гибернейтовский Transaction manager. Он нужен, чтобы наладить декларативное управление транзакциями в контексте Spring Framework
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private final Properties hibernateProperties() { //Дополнительные настройки для гибернейта
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
                "hibernate.hbm2ddl.auto", "create-drop"); //авто ддл. ДДЛ(Data Definition Language - Язык определения данных) - это когда мы создаем наши структуры (таблицы).
        // Когда гибернейт просканирует все сущности помеченные аннотацией entity и если у нас включен авто ддл,
        // то гибернейт САМ СОЗДАСТ таблицы на основании этих классов
        //Используется стратегия create-drop (когда запускаем программу - все старое дропается и создается новое)
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");//диалект, чтобы
        // гибернейт понял с какой БД он работает
        hibernateProperties.setProperty("show_sql ", "true"); //чтобы в логах показывали все, что надо
        return hibernateProperties;
    }
}
