package tms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//Для того чтобы контейнер работал, ему нужно передавать конфигурацию
//Есть 3 типа конфигураций:
//annotation base - конфигурация на основе аннотаций (Когда мы хотим создать экземпляр кода чужого класса)
//java base - конфигурация на основе классов (Когда мы хотим создать экземпляр кода своего класса)
//xml эта никому не нужна
//Эти 3 вида конфигурации объясняют одно и то же, просто разными стилями
//Объясняют что создавать контейнеру и кого внедрять в кого
//Разные виды конфигураций можно совмещать
//Виды внедрения:(Мы говорим фреймворку создать за нас бины (иначе говоря — объекты) и внедрить их в другие бины. И фреймворк это делает.)
//constructor - внедрение через конструктор(самый нативный )
//setter - внедрение через сеттер
//field - внедрение через поле
//Внедрения используют, чтобы внедрить ссылку одного объекта на другой объект
//Все бины спринга - синглтоны(одиночки)


public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext =   //interface of our container
                new AnnotationConfigApplicationContext(RootConfiguration2.class); //сюда надо передать конфигурацию
        User user = (User) applicationContext.getBean("user");
        user.findMe();
    }
}
