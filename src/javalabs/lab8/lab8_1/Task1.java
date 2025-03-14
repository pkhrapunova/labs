package javalabs.lab8.lab8_1;

public class Task1 {
    public static void main(String[] args) {
        System.out.println("Главный поток начал работу");

        Thread thread1 = new Thread(new MyThread(1));
        Thread thread2 = new Thread(new MyThread(2));
        Thread thread3 = new Thread(new MyThread(3));
        Thread thread4 = new Thread(new MyThread(4));
        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        thread4.start();

        try {
            thread4.join();
            thread3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Главный поток закончил работу");
    }
}