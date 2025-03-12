package javalabs.lab8.lab8_1;

public class MyThread extends Thread{
    private final int id;
    private final Thread[] dependencies;

    public MyThread(int id, Thread...dependencies) {
        this.id = id;
        this.dependencies = dependencies;
    }
    @Override
    public void run() {
        try {
            if (dependencies != null) {
                for (Thread dependency : dependencies) {
                    if (dependency != null) {
                        dependency.join();
                    }
                }
            }
            System.out.println("Поток " + id + " начал работу.");
            Thread.sleep((int) (Math.random() * (5000 - 2000 + 1)));
            System.out.println("Поток " + id + " закончил работу.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
