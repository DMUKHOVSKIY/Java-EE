import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

//Thread - class
//Runnable - interface

//start() - стартуем как поток
//run() - стартуем как обычный метод

//у каждого потока есть:
// 1)состояние
// 2)приоритет (по умолчанию - 5)
// 3)название потока

//Атомарная операция - либо выполняется целиком,
// либо не выполняется вовсе

public class Main {
    private AtomicInteger ai = new AtomicInteger();//потокобезопасный Integer
    // класса util.concurrent

    public void test(){
        Vector<String> a = new Vector<>(); //коллекция, то же самое, что и ArrayList, только потокобезопасная, (устарела)
        Hashtable<String,String> b = new Hashtable<>();//коллекция, то же самое, что и HashMap,
        // только потокобезопасная + null в качестве
        // ключа хранить нельзя, как в HashMap(можно), (устарела)
        int i = ai.incrementAndGet();
        CopyOnWriteArrayList<String> c = new CopyOnWriteArrayList<>();//потокобезопасный ArrayList (не устарел)
        ConcurrentHashMap<String,String> d = new ConcurrentHashMap<>();//потокбезопасная HashMap (не утсарела)
    }

    public static void main(String[] args) {
        /*Thread thread = new SomeThread();
        thread.start();
        new SomeThread().start();
        for (int i = 0; i < 100; i++){
            System.out.println("main" + i);
        }*/
        Thread thread = new Thread(new SomeRun());
        Thread thread1 = new Thread(new SomeRun());
        thread1.setDaemon(true);//поток демон(имеет самый низкий приоритет)
        // main не ожидает потоки демоны, в отличие от остальных потоков
        // (завершает работу при окончании последнего потока не демона).
        // Потоки демоны обычно: самые фоновые вещи (например сборка мусора)
        //
        // сначалоа объявляем поток демон,
        // потом "стартуем поток"
        thread1.setPriority(10); //приоритет
        thread1.setName("Thread"); //кастомное имя
        thread.start();
        thread1.start();

    }
}
