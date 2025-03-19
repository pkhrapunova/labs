package javalabs.lab8.lab8_3;

import javalabs.lab8.lab8_3.Model.MaterialConsumption;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class CollectionController extends Thread {
    private static final ReentrantLock consoleLock = new ReentrantLock();
    private final ReentrantLock collectionLock;
    private final MaterialConsumptionModel model;
    private final MaterialConsumptionView view;
    private final int iterations;

    public CollectionController(MaterialConsumptionModel model, MaterialConsumptionView view, int iterations, ReentrantLock collectionLock) {
        this.model = model;
        this.view = view;
        this.iterations = iterations;
        this.collectionLock = collectionLock;
    }

    @Override
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
                    default -> {
                        consoleLock.lock();
                        try {
                            System.out.println(threadName + " Incorrect input, try again.");
                        } finally {
                            consoleLock.unlock();
                        }
                    }
                }
            } catch (CustomException e) {
                consoleLock.lock();
                try {
                    System.out.println("Ошибка: " + e.getMessage());
                } finally {
                    consoleLock.unlock();
                }
            }
        }
        consoleLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " завершил работу.");
        } finally {
            consoleLock.unlock();
        }
    }

    public void addElement(String threadName) throws CustomException {
        collectionLock.lock();
        try {
            MaterialConsumption item = DataGenerator.generateMaterialConsumption();
            model.add(item);
            consoleLock.lock();
            try {
                System.out.println(threadName + " Добавлен элемент: " + item);
            } finally {
                consoleLock.unlock();
            }
        } finally {
            collectionLock.unlock();
        }
    }

    public void updateElement(String threadName, int index) throws CustomException {
        collectionLock.lock();
        try {
            if (model.getSize() > 0 && index >= 0 && index < model.getSize()) {
                MaterialConsumption updatedItem = DataGenerator.generateMaterialConsumption();
                model.update(index, updatedItem);
                consoleLock.lock();
                try {
                    System.out.println(threadName + " Обновлен элемент по индексу " + index+" :"+updatedItem);
                } finally {
                    consoleLock.unlock();
                }
            } else {
                consoleLock.lock();
                try {
                    System.out.println(threadName + " Некорректный индекс или коллекция пуста.");
                } finally {
                    consoleLock.unlock();
                }
            }
        } finally {
            collectionLock.unlock();
        }
    }

    public void deleteElement(String threadName, int index) throws CustomException {
        collectionLock.lock();
        try {
            if (model.getSize() > 0 && index >= 0 && index < model.getSize()) {
                model.delete(index);
                consoleLock.lock();
                try {
                    System.out.println(threadName + " Удален элемент по индексу " + index);
                } finally {
                    consoleLock.unlock();
                }
            } else {
                consoleLock.lock();
                try {
                    System.out.println(threadName + " Некорректный индекс");
                } finally {
                    consoleLock.unlock();
                }
            }
        } finally {
            collectionLock.unlock();
        }
    }

    public void printAllElements(String threadName) throws CustomException {
        MaterialConsumption[] items;
        collectionLock.lock();
        try {
            items = model.getAll();
        } finally {
            collectionLock.unlock();
        }

        consoleLock.lock();
        try {
            if (items == null || items.length == 0) {
                System.out.println(threadName + " Коллекция пуста.");
            } else {
                System.out.println(threadName + " Текущая коллекция:");
                for (MaterialConsumption item : items) {
                    System.out.println(item.toString());
                }
            }
        } finally {
            consoleLock.unlock();
        }
    }

    public void saveToFile(String threadName) throws CustomException {
        collectionLock.lock();
        try {
            if (model.getSize() > 0) {
                String filename = "data_" + threadName + ".json";
                model.saveToFile(filename);
                consoleLock.lock();
                try {
                    System.out.println(threadName + " Данные сохранены в файл: " + filename);
                } finally {
                    consoleLock.unlock();
                }
            } else {
                consoleLock.lock();
                try {
                    System.out.println(threadName + " Коллекция пуста. Сохранение не требуется.");
                } finally {
                    consoleLock.unlock();
                }
            }
        } finally {
            collectionLock.unlock();
        }
    }

    public void loadFromFile(String threadName) throws CustomException {
        collectionLock.lock();
        try {
            model.loadFromFile("1.json");
            consoleLock.lock();
            try {
                System.out.println(threadName + " Данные загружены из файла: " + "1.json");
            } finally {
                consoleLock.unlock();
            }
        } finally {
            collectionLock.unlock();
        }
    }
}