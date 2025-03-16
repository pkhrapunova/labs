package javalabs.lab8.lab8_3;

import javalabs.lab8.lab8_3.Model.MaterialConsumption;

import java.util.Random;

public class CollectionController{
    private final MaterialConsumptionModel model;
    private final MaterialConsumptionView view;

    public CollectionController(MaterialConsumptionModel model,MaterialConsumptionView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        int executionCount = 0; // Счетчик вызовов потоков
        while (executionCount < 20) { // Ограничение на 10 вызовов
            view.Menu();
            int choice = view.choice();
            System.out.println(choice);
            try {
                switch (choice) {
                    case 1:
                        Thread addThread = new Thread(new AddTask(model));
                        addThread.start();
                        break;
                    case 2:
                        Thread updateThread = new Thread(new UpdateTask(model));
                        updateThread.start();
                        break;
                    case 3:
                        Thread deleteThread = new Thread(new DeleteTask(model));
                        deleteThread.start();
                        break;
                    case 4:
                        Thread printThread = new Thread(new PrintTask(model));
                        printThread.start();
                        break;
                    case 5:
                        Thread saveThread = new Thread(new SaveTask(model));
                        saveThread.start();
                        break;
                    case 6:
                        Thread loadThread = new Thread(new LoadTask(model));
                        loadThread.start();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Неверный ввод, попробуйте снова.");
                        continue;
                }
                executionCount++; // Увеличиваем счетчик после каждого успешного вызова потока
                System.out.println("Выполнено операций: " + executionCount + " из 10");
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        System.out.println("Программа завершена. Выполнено 10 операций.");
    }

    private static class AddTask implements Runnable {
        private final MaterialConsumptionModel model;

        public AddTask(MaterialConsumptionModel model) {
            this.model = model;
        }

        @Override
        public void run() {
            MaterialConsumption item = DataGenerator.generateMaterialConsumption();
            model.add(item);
            System.out.println("Элемент добавлен: " + item);
        }
    }

    private static class UpdateTask implements Runnable {
        private final MaterialConsumptionModel model;

        public UpdateTask(MaterialConsumptionModel model) {
            this.model = model;
        }

        @Override
        public void run() {
            try {
                if (model.getSize() == 0) {
                    System.out.println("Коллекция пуста. Нечего обновлять.");
                    return;
                }

                int index = new Random().nextInt(model.getSize()); // Случайный индекс
                MaterialConsumption updatedItem = DataGenerator.generateMaterialConsumption();
                model.update(index, updatedItem);
                System.out.println("Элемент обновлен по индексу " + index + ": " + updatedItem);
            } catch (CustomException e) {
                System.out.println("Ошибка при обновлении: " + e.getMessage());
            }
        }
    }

    private static class DeleteTask implements Runnable {
        private final MaterialConsumptionModel model;

        public DeleteTask(MaterialConsumptionModel model) {
            this.model = model;
        }

        @Override
        public void run() {
            try {
                if (model.getSize() == 0) {
                    System.out.println("Коллекция пуста. Нечего удалять.");
                    return;
                }

                int index = new Random().nextInt(model.getSize());
                model.delete(index);
                System.out.println("Элемент удален по индексу: " + index);
            } catch (CustomException e) {
                System.out.println("Ошибка при удалении: " + e.getMessage());
            }
        }
    }

    private static class PrintTask implements Runnable {
        private final MaterialConsumptionModel model;

        public PrintTask(MaterialConsumptionModel model) {
            this.model = model;
        }

        @Override
        public void run() {
            try {
                MaterialConsumption[] items = model.getAll();
                if (items == null || items.length == 0) {
                    System.out.println("Коллекция пуста.");
                } else {
                    System.out.println("Текущая коллекция:");
                    for (MaterialConsumption item : items) {
                        System.out.println(item);
                    }
                }
            } catch (CustomException e) {
                System.out.println("Ошибка при выводе коллекции: " + e.getMessage());
            }
        }
    }

    private static class SaveTask implements Runnable {
        private final MaterialConsumptionModel model;

        public SaveTask(MaterialConsumptionModel model) {
            this.model = model;
        }

        @Override
        public void run() {
            try {
                String filename = "data_" + System.currentTimeMillis() + ".json";
                model.saveToFile(filename);
            } catch (CustomException e) {
                System.out.println("Ошибка при сохранении: " + e.getMessage());
            }
        }
    }

    private static class LoadTask implements Runnable {
        private final MaterialConsumptionModel model;

        public LoadTask(MaterialConsumptionModel model) {
            this.model = model;
        }

        @Override
        public void run() {
            try {
                String filename = "1.json"; // Имя файла для загрузки
                model.loadFromFile(filename);
                System.out.println("Коллекция загружена из файла: " + filename);
            } catch (CustomException e) {
                System.out.println("Ошибка при загрузке: " + e.getMessage());
            }
        }
    }
}