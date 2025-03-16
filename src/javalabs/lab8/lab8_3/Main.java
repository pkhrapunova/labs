package javalabs.lab8.lab8_3;


//public class Main {
//    public static void main(String[] args) {
//        MaterialConsumptionModel model = new MaterialConsumptionModel();
//        MaterialConsumptionView view = new MaterialConsumptionView();
//
//        for (int i = 0; i < 6; i++) {
//            CollectionController controller = new CollectionController(model, view, 3);
//            Thread thread = new Thread(controller::run);
//            thread.start();
//        }
//    }
//}


import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        MaterialConsumptionModel model = new MaterialConsumptionModel();
        MaterialConsumptionView view = new MaterialConsumptionView();
        int iterations = 1; // Количество итераций для каждого потока

        // Создаем CountDownLatch для синхронизации потоков
        CountDownLatch latch = new CountDownLatch(6);

        // Поток для добавления элементов
        Thread addThread = new Thread(() -> {
            CollectionController controller = new CollectionController(model, view, iterations);
            for (int i = 0; i < iterations; i++) {
                try {
                    controller.addElement(Thread.currentThread().getName());
                } catch (CustomException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }
            latch.countDown(); // Уменьшаем счетчик
        });

        // Поток для обновления элементов
        Thread updateThread = new Thread(() -> {
            CollectionController controller = new CollectionController(model, view, iterations);
            for (int i = 0; i < iterations; i++) {
                try {
                    int index = new Random().nextInt(model.getSize() + 1);
                    controller.updateElement(Thread.currentThread().getName(), index);
                } catch (CustomException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }
            latch.countDown(); // Уменьшаем счетчик
        });

        // Поток для удаления элементов
        Thread deleteThread = new Thread(() -> {
            CollectionController controller = new CollectionController(model, view, iterations);
            for (int i = 0; i < iterations; i++) {
                try {
                    int index = new Random().nextInt(model.getSize() + 1);
                    controller.deleteElement(Thread.currentThread().getName(), index);
                } catch (CustomException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }
            latch.countDown(); // Уменьшаем счетчик
        });

        // Поток для вывода всех элементов
        Thread printThread = new Thread(() -> {
            CollectionController controller = new CollectionController(model, view, iterations);
            for (int i = 0; i < iterations; i++) {
                try {
                    controller.printAllElements(Thread.currentThread().getName());
                } catch (CustomException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }
            latch.countDown(); // Уменьшаем счетчик
        });

        // Поток для сохранения в файл
        Thread saveThread = new Thread(() -> {
            CollectionController controller = new CollectionController(model, view, iterations);
            for (int i = 0; i < iterations; i++) {
                try {
                    controller.saveToFile(Thread.currentThread().getName());
                } catch (CustomException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }
            latch.countDown(); // Уменьшаем счетчик
        });

        // Поток для загрузки из файла
        Thread loadThread = new Thread(() -> {
            CollectionController controller = new CollectionController(model, view, iterations);
            for (int i = 0; i < iterations; i++) {
                try {
                    controller.loadFromFile(Thread.currentThread().getName());
                } catch (CustomException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }
            latch.countDown(); // Уменьшаем счетчик
        });

        // Запускаем все потоки
        addThread.start();
        updateThread.start();
        deleteThread.start();
        printThread.start();
        saveThread.start();
        loadThread.start();

        // Ожидаем завершения всех потоков
        try {
            latch.await();
        } catch (InterruptedException e) {
            System.out.println("Главный поток был прерван: " + e.getMessage());
        }

        System.out.println("Все потоки завершили выполнение.");
    }
}