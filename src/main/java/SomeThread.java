public class SomeThread extends Thread{
    //когда наследуемся, то имеем большую функциональность
    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            System.out.println(this.getName() + " " + i);
        }
    }
}
