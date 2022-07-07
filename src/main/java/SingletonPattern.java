public class SingletonPattern {
//    Нам нужно, чтобы экземпляр класса был один
    private static SingletonPattern instance;

    //Потокобезопасный Singletone
   /* private static volatile SingletonPattern instance;
    public static SingletonPattern getInstance(){
    synchronized (SingletonPattern.class){
     if(instance==null){
            instance = new SingletonPattern();
        }
         return instance;
    }
    }*/

    //Жадная инициализация: экземпляр будет создан, когда будет упомянут SingletonPattern
//    private final static SingletonPattern instance = new SingletonPattern();
//    public static SingletonPattern getInstance(){
//        return instance;
//    }

    private SingletonPattern(){} //Мы нигде не можем создать экземпляр класса, кроме как в самом классе (наследоваться тоже не можем)
   /* private SingletonPattern(){ //Чтобы нельзя было вызывать конструктор через рефлексию
        throw new RuntimeException();
    }*/

    //Ленивая инициализация (Lazy initialization): только когда будет вызыван метод будет создат экземпляр
    public static SingletonPattern getInstance(){
        if(instance==null){
            instance = new SingletonPattern();
        }
        return instance;
    }

    public double process(double a, double b) {
        return a + b;
    }

    public static void main(String[] args) {
        SingletonPattern instance1 = SingletonPattern.getInstance();
        SingletonPattern instance2 = SingletonPattern.getInstance();
        System.out.println(instance1==instance2); //true
    }
}
