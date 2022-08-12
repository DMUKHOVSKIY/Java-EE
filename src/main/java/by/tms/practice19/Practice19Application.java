package by.tms.practice19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //запускает сканирование нашего проекта, ищет все компоненты, все бины, все конфигурации
public class Practice19Application {//Application class - название
    //Этот метод трогать не нужно. Это уже сгенерированный Application Class(запускает Application Context
    //(который передает конфигурационную информацию приложению)) для бута (это основная точка входа в приложение)
    //Можно только указывать проверки на аргументы
    public static void main(String[] args) {
        SpringApplication.run(Practice19Application.class, args);
    }

}
