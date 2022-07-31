package tms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component //А это уже нужно для annotation base конфигурации
public class User {
   // @Autowired //Если сеттеров нет, то можно пометить аннотацией поля, но так делать не рекомендуется
    //Напримере: Если переносить класс User на другой фреймворк эта аннотация не работает(Это аннотация самого спринга)
    //или нам надо протестировать класс User, а нет ни сеттеров, ни конструкторов. (Спринг работает через рефлексию)
    private Animal cat;
    //@Autowired
    private Animal dog;

    @Autowired //Помечаем, чтобы этот конструктор вызывался обязательно, а другие конструкторы - игнорируются
    public User(/*@Qualifier("тут можем выбрать название бина, если  cat не подходит")*/Animal cat, Animal dog) {
        this.cat = cat;
        this.dog = dog;
    }

    public User() {  //Спринг действует по пути наименьшего сопротивления. И если у нас 2 конструктора (с параметрами и без),
        //то вызывается конструктор без параметров
    }
//    @Autowired //Автосвязывание. Если у нас нет конструктора, то User не получит ссылок на кота и собаку
//    //Для этого мы создаем сеттеры и помечаем их аннотацией (@Autowired)
//    public void setCat(Cat cat) {
//        this.cat = cat;
//    }
//
//    public void setDog(Dog dog) {
//        this.dog = dog;
//    }

    public void findMe(){
        System.out.println(cat);
        System.out.println(dog);
    }
}
