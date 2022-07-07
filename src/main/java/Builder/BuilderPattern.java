package Builder;

public class BuilderPattern {
    //Мы можем конфигурировать объект через сеттеры или конструктор,
    //а можем через билдер(как метод .append() StringBuilder-а)

//    Сеттеров нет, переменные - приватные(не паримся по поводу доступа)
    //Можно сделать кучу конструкторв(когда User неизменный (нельзя использоавать сеттеры)),
    //но это неправильно, а так мы добавляем те поля, которые нам нужны
    public static void main(String[] args) {
        User user = new User.Builder()
                .name("Alex")
                .username("Nagibator2009")
                .password("123456789")
                .build();

    }
}
