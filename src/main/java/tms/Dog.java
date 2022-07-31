package tms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.xml.crypto.dsig.DigestMethod;

@Component //А это уже нужно для annotation base конфигурации
//@Scope("singleton") - идёт по умолчанию
//@Scope("prototype") - чтобы каждый раз создавался новый класс
//@Lazy - Мы знаем, что все наши сервлеты - Lazy (создаются только тогда, когда их вызывают). В спринге наоборот - все создается сразу. Lazy говорит, что  cat будет создан, когда кому-то понадобится, а не при создании контейнера с конфигурацией
public class Dog implements Animal {
    //@Value("Test Dog")
    private String name;

    public Dog(@Value("Test Dog") String name) { //Так мы засовываем имя в дога
        this.name = name;
    }

    @PostConstruct //для того, чтобы работал метод (надо подключить библиотеку)
    public void init(){
        System.out.println("Dog Init");
    }

    @PreDestroy //для того, чтобы работал метод
    public void destroy(){
        System.out.println("Dog Destroy");
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}
