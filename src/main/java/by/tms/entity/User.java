package by.tms.entity;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.*;

//чтобы постоянно не добавлять конструкторы, геттеры, сеттеры. Мы можем подключить библиотеку под названием lombok(это плагин - независимо компилируемый программный модуль+динамически подключается)
//Эта библиотека упрощает нам жизнь при описании каких-либо entity
@Data //ломбоковская аннотация, которая помогает добавить всё, что нужно к user-у(в .class)
//@Builder//тоже фишка ломбока. Мы можем использовать паттерны, не описывая их в классе. Тут Builder
//@Slf4j//тоже фишка ломбока. Добавляет логгер и мы можем его легко использовать
public class User {
    //Надо указать, что мы будем валидировать. Указываем ограничения (constraints)
    @NotNull
    @NotBlank // "" - true(пустая), " " - true(пустая), "a" - false(не пустая)
    @NotEmpty // "" - true(пустая), " " - false(не пустая)
    private String name;
    @NotNull(message = "Must not be null")//вместо defaultMessage будет писать наш код
    @NotBlank
    @NotEmpty
    private String username;
    @NotNull
    @NotBlank
    @NotEmpty
    //@Pattern(regexp = "...") //... - регулярное выражения для пароля
    private String password;
//    @Max(value = 100, message = "Must be less than 100")
//    @Min(value = 1, message = "Must be greater than 1")
//    private int age;


    //При этом мы можем написать свой кастомный геттер и ломбок поймёти, что наш преимущественнее и не будет генерировать свой
}
