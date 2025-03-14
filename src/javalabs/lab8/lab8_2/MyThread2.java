package javalabs.lab8.lab8_2;

import java.util.Random;

public class MyThread2 extends Thread{
    private final int id;
    private final int n;

    public MyThread2(int id,int n) {
        this.id = id;
        this.n = n;
    }
    @Override
    public void run() {
        try {
            int[] array = new int[n];
            Random rand = new Random();
            for (int i = 0; i < array.length; i++) {
                array[i] = rand.nextInt(201) - 100;
            }
            int[] result = rearrangeArray(array);
            System.out.println("Поток " + id + " начал работу.");
            System.out.println("Поток " + id  +" Входной массив: "+ java.util.Arrays.toString(array));
            System.out.println("Поток " + id  +" Выходной массив: "+ java.util.Arrays.toString(result));
            Thread.sleep((int) (Math.random() * (5000 - 2000 + 1)));
            System.out.println("Поток " + id + " закончил работу.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static int[] rearrangeArray(int[] array) {
        int[] result = new int[array.length];
        int index = 0;
        for (int num : array) {
            if (num >= 0) {
                result[index] = num;
                index++;
            }
        }
        for (int num : array) {
            if (num < 0) {
                result[index] = num;
                index++;
            }
        }
        return result;
    }
}
