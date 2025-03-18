package javalabs.lab8.lab8_3;

import javalabs.lab8.lab8_3.Model.MaterialConsumption;
import java.util.Random;

public class CollectionController {
    private final MaterialConsumptionModel model;
    private final MaterialConsumptionView view;
    private final int iterations;

    public CollectionController(MaterialConsumptionModel model, MaterialConsumptionView view, int iterations) {
        this.model = model;
        this.view = view;
        this.iterations = iterations;
    }

    public void run() {
        for (int i = 0; i < iterations; i++) {
            int action = view.choice();
            String threadName = Thread.currentThread().getName();
            try {
                int index = new Random().nextInt(model.getSize() + 1);
                switch (action) {
                    case 0 -> addElement(threadName);
                    case 1 -> updateElement(threadName, index);
                    case 2 -> deleteElement(threadName, index);
                    case 3 -> printAllElements(threadName);
                    case 4 -> saveToFile(threadName);
                    case 5 -> loadFromFile(threadName);
                    default -> System.out.println(threadName + " Incorrect input, try again.");
                }
            } catch (CustomException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        System.out.println(Thread.currentThread().getName() + " завершил работу.");
    }

    public void addElement(String threadName) throws CustomException {
        MaterialConsumption item = DataGenerator.generateMaterialConsumption();
        model.add(item);
        System.out.println(threadName + " Добавлен элемент: " /*+ item*/);
    }

    public void updateElement(String threadName, int index) throws CustomException {
        if (model.getSize() > 0) {
            MaterialConsumption updatedItem = DataGenerator.generateMaterialConsumption();
            model.update(index, updatedItem);
            System.out.println(threadName + " Обновлен элемент по индексу " + index/* + ": " + updatedItem*/);
        } else {
            System.out.println(threadName + " Коллекция пуста. Обновление невозможно.");
        }
    }

    public void deleteElement(String threadName, int index) throws CustomException {
        if (model.getSize() > 0) {
            model.delete(index);
            System.out.println(threadName + " Удален элемент по индексу " + index);
        } else {
            System.out.println(threadName + " Коллекция пуста. Удаление невозможно.");
        }
    }

    public void printAllElements(String threadName) throws CustomException {
        MaterialConsumption[] items = model.getAll();
        if (items == null || items.length == 0) {
            System.out.println(threadName + " Коллекция пуста.");
        } else {
            System.out.println(threadName + " Текущая коллекция:");
            for (MaterialConsumption item : items) {
                System.out.println(item.toString());
            }
        }
    }

    public void saveToFile(String threadName) throws CustomException {
        if (model.getSize() > 0) {
            String filename = "data_" + threadName + ".json";
            model.saveToFile(filename);
            System.out.println(threadName + " Данные сохранены в файл: " + filename);
        } else {
            System.out.println(threadName + " Коллекция пуста. Сохранение не требуется.");
        }
    }

    public void loadFromFile(String threadName) throws CustomException {
        model.loadFromFile("1.json");
        System.out.println(threadName + " Данные загружены из файла: " + "1.json");
    }
}