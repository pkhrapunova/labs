package javalabs.lab6.lab6_2;

public class Collection<T> {
    private DynamicArray<T> collection;

    public Collection() {
        collection = new DynamicArray<>();
    }

    // Добавить элемент в коллекцию
    public void add(T item) {
        collection.add(item);
    }

    // Обновить элемент коллекции по индексу
    public void update(int index, T item) throws CustomException {
        if (index < 0 || index >= collection.size()) {
            throw new CustomException("Index out of bounds");
        }
        collection.update(index, item);
    }

    // Удалить элемент коллекции по индексу
    public void delete(int index) throws CustomException {
        if (index < 0 || index >= collection.size()) {
            throw new CustomException("Index out of bounds");
        }
        collection.delete(index);
    }

    // Вывести все элементы коллекции
    public void printAll() throws CustomException {
        if (collection.isEmpty()) {
            throw new CustomException("There are no items in the collection");
        }
        collection.printAll();
    }
}
