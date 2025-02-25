package javalabs.lab6.lab6_2;

import javalabs.lab6.lab6_2.Model.*;
import javalabs.lab6.lab6_2.Model.Record;

public class Collection<T> {
    private final DynamicCollection<T> collection;

    public Collection() {
        collection = new DynamicCollection<>();
    }

    // Проверка, пуста ли коллекция
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    // Добавить элемент в коллекцию
    public void add(String choice) throws CustomException {
        T item = createElement(choice);
        collection.add(item);
    }

    // Обновить элемент коллекции по индексу
    public void update(int index, String choice) throws CustomException {
        if (isEmpty()) {
            throw new CustomException("There are no items in the collection");
        }
        if (index < 0 || index >= collection.size()) {
            throw new CustomException("Index out of bounds");
        }
        T item = createElement(choice);
        collection.update(index, item);
    }

    // Удалить элемент коллекции по индексу
    public void delete(int index) throws CustomException {
        if (isEmpty()) {
            throw new CustomException("There are no items in the collection");
        }
        if (index < 0 || index >= collection.size()) {
            throw new CustomException("Index out of bounds");
        }
        collection.delete(index);
    }

    // Вывести все элементы коллекции
    public void printAll() throws CustomException {
        if (isEmpty()) {
            throw new CustomException("There are no items in the collection");
        }
        collection.printAll();
    }

    // Метод создания элементов
    private T createElement(String choice) throws CustomException {
        switch (choice) {
            case "1":
                MaterialConsumption materialConsumption = new MaterialConsumption();
                materialConsumption.scan();
                return (T) materialConsumption;
            case "2":
                Client client = new Client();
                client.scan();
                return (T) client;
            case "3":
                Master master = new Master();
                master.scan();
                return (T) master;
            case "4":
                Material material = new Material();
                material.scan();
                return (T) material;
            case "5":
                Record record = new Record();
                record.scan();
                return (T) record;
            default:
                throw new CustomException("Invalid choice! Please select a valid class.");
        }
    }
}
