package tms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Когда мы работаем через java base конфигурацию, то мы обязаны сами создавать объекты в методах
//Но регулировать все надо в 1 классе и это уже хорошо
//Основной смысл - User, Cat and Dog - стали менее зависимые друг от друга
@Configuration //эта аннотация нужна для спринга, чтобы понимать, что это класс конфигурации
public class RootConfiguration { //Это конфигурация

//    @Bean //компоненты IoC контейнера - те объекты, которые лежат внутри Spring контейнера(все типа Object)
//    public User user(Cat cat, Dog dog){
//        return new User(cat, dog);
//    }
//
//    @Bean
//    public Cat cat(){
//        return new Cat("Tesst Cat");
//    }
//
//    @Bean //Если мы конфигурируем через java base, то спринг берет название бинов/методов (в контейнере) из названия метода(название метода - название бина)
//    public Dog dog(){
//        return new Dog("Tesst Dog");
//    }
}
