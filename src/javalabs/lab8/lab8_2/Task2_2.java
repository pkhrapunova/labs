package javalabs.lab8.lab8_2;

public class Task2_2 {
    public static void main(String[] args) {
        System.out.println("Главный поток начал работу");

        int n = 10;

        MyThread2 thread1 = new MyThread2(1,n);
        MyThread2 thread2 = new MyThread2(2,n);
        MyThread2 thread3 = new MyThread2(3,n);
        MyThread2 thread4 = new MyThread2(4,n);
        thread1.start();
        thread2.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        thread4.start();

        try {
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        thread3.start();

        try {
            thread4.join();
            thread3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Главный поток закончил работу");
    }
}
