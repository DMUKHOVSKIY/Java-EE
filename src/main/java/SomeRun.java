public class SomeRun implements Runnable{
    //при реализации интерфейса иммем меньшую функциональность для потока
    @Override
    public void run() {
        for (int i = 0; i<100; i++){
            System.out.println("Thread " + i);
        }
    }
}
