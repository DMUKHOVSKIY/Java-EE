package tms;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Class {
    /*Все классы наследуются от класса Object(ограниченное множественное наследование).
     *Object объясняет то, какиме характерные черты имеет объект и что с ним в принципе можно делать.
     *Наш калсс в будущем будет объектом со своим поведением. В классе Object нет свойств, которые что-то определяют,
     *но есть поведение.
     * Рефлексия - библиотека, которая помогает исследовать чужой написанный класс
     * Как создать экземпляр класса без использования оператора new - через newInstance() какого-либо конструктора в рефлексии
     * Рефлексия используется в библиотеках и фреймворках, но не для бизнес логики*/

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        User user = new User();

        java.lang.Class<? extends User> aClass = user.getClass(); // 1 способ. Работает только после того, как мы создали экземпляр класса. В режиме работающей программы
        Object o = aClass.getConstructors()[0].newInstance(); // создаем экземпляр класса. То же самое, что и User user = new User(); Нужно передать параметры в конструктор

        java.lang.Class<User> userClass = User.class;// 2 способ. Объект не создан. Если это библиотека и никто ничего не создал. Нам нужно проанализировать написанный другим программистом класс
        User user1 = userClass.newInstance();// Устаревший вариант создания экземпляра класса. Не принимает параметров.

        System.out.println(Arrays.toString(userClass.getFields()));// Берём все public поля класса User
        System.out.println(Arrays.toString(userClass.getDeclaredFields()));// Берём вообще все поля

        Field username = userClass.getDeclaredField("username");
        username.setAccessible(true);// Так как поле приватное, мы должны разрешить к нему доступ.
        System.out.println(user);
        username.set(user, "Test");// В объект user мы должны засунуть в поле username значение Test
        System.out.println(user);
    }


}
