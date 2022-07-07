package Factory;

public class FactoryPattern {
    //Это некий объект, который может создавать более кастомные объекты(это конструктор)
//В отличие от фабричного метода(метод внутри того, что он и возварщает с какими-нибудь примудреностями)
// фабрика - это отдельный класс/объект, которй делает то, что ему скажут.
    public User createUser(Role role){
        switch(role){
            case USER:
                return new User(Role.USER);
            case ADMIN:
                return new User(Role.ADMIN);
            case MODERATOR:
                return new User(Role.MODERATOR);
        }
        throw new RuntimeException();
    }

    public static void main(String[] args) {
       FactoryPattern factoryPattern = new FactoryPattern();
       User user = factoryPattern.createUser(Role.ADMIN);
    }
}
