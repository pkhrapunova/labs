package javalabs.lab6.lab6_3;

import java.util.Arrays;

public class DynamicCollection<T> {
    private Object[] elements;
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    public DynamicCollection() {
        this.elements = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T element) {
        if (size == elements.length) {
            increaseCapacity();
        }
        elements[size++] = element;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= 0 && index < size) {
            return (T) elements[index];
        }
        throw new IndexOutOfBoundsException("Index out of range");
    }

    public void update(int index, T element) throws CustomException {
        if (index < 0 || index >= size) {
            throw new CustomException("Index out of bounds");
        }
        elements[index] = element;
    }

    public void delete(int index) throws CustomException {
        if (index < 0 || index >= size) {
            throw new CustomException("Index out of bounds");
        }
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
    }

    public void printAll() throws CustomException {
        if (isEmpty()) {
            throw new CustomException("There are no items in the collection");
        }
        for (int i = 0; i < size; i++) {
            System.out.println(elements[i]);
        }
    }

    private void increaseCapacity() {
        int newCapacity = elements.length * 2;
        elements = Arrays.copyOf(elements, newCapacity);
    }
}