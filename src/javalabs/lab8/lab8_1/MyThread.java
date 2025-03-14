package javalabs.lab8.lab8_1;

public class MyThread extends Thread{
    private final int id;

    public MyThread(int id) {
        this.id = id;
    }
    @Override
    public void run() {
        try {
            System.out.println("Поток " + id + " начал работу.");
            Thread.sleep((int) (Math.random() * (5000 - 2000 + 1)));
            System.out.println("Поток " + id + " закончил работу.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
