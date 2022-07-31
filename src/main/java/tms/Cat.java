package tms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component //А это уже нужно для annotation base конфигурации
public class Cat implements Animal {
// @Value("Test Cat") Так мы можем засунуть сюда значение, когда работаем с annotation base конфигурацией (не работает)
private String catName;

    public Cat(String catName) {
        this.catName = catName;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + catName + '\'' +
                '}';
    }
}
