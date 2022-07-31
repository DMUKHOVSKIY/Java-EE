package tms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//Если мы конфигурируем через annotation base, то спринг берет название бинов/методов (в контейнере) из названия класса(название класса - название бина). С маленькой буквы!

@Configuration
@ComponentScan(basePackages = "tms")//Сканируем все классы на наличие аннотации Component
public class RootConfiguration2 {
    @Bean//В классе с аннотацией Component - Spring сам создает его экземпляр
    //объекты чужих классов надо создавать через аннотацию @Bean, а свои - @Component
    public
    String catName(){
        return "Test Catttt";
    }

    String dog(){
        return "Test dog";
    }
}
