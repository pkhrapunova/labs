package javalabs.lab6.lab6_2;

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

    public void update(int index, T element) {
        if (index >= 0 && index < size) {
            elements[index] = element;
        } else {
            throw new IndexOutOfBoundsException("Index out of range");
        }
    }

    public void delete(int index) {
        if (index >= 0 && index < size) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
            elements[--size] = null;
        } else {
            throw new IndexOutOfBoundsException("Index out of range");
        }
    }

    private void increaseCapacity() {
        int newCapacity = elements.length * 2;
        elements = Arrays.copyOf(elements, newCapacity);
    }

    public void printAll() {
        for (int i = 0; i < size; i++) {
            System.out.println(elements[i]);
        }
    }
}