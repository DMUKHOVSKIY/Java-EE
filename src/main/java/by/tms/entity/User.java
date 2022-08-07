package by.tms.entity;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

//чтобы постоянно не добавлять конструкторы, геттеры, сеттеры. Мы можем подключить библиотеку под названием lombok(это плагин - независимо компилируемый программный модуль+динамически подключается)
//Эта библиотека упрощает нам жизнь при описании каких-либо entity
@Data //ломбоковская аннотация, которая помогает добавить всё, что нужно к user-у(в .class)
//@Builder//тоже фишка ломбока. Мы можем использовать паттерны, не описывая их в классе. Тут Builder
//@Slf4j//тоже фишка ломбока. Добавляет логгер и мы можем его легко использовать
public class User {
    private String name;
    private String username;
    private String password;

    //При этом мы можем написать свой кастомный геттер и ломбок поймёти, что наш преимущественнее и не будет генерировать свой
}
