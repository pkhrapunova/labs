package javalabs.lab8.lab8_3;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

public class DynamicCollection<T> {
    private Object[] elements;
    private int size;
    private static final int INITIAL_CAPACITY = 10;
    private final ReentrantLock lock = new ReentrantLock();

    public DynamicCollection() {
        this.elements = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public void add(T element) {
        lock.lock();
        try {
            if (size == elements.length) {
                increaseCapacity();
            }
            elements[size++] = element;
        } finally {
            lock.unlock();
        }
    }

    private void increaseCapacity() {
        int newCapacity = elements.length * 2;
        elements = Arrays.copyOf(elements, newCapacity);
    }

    @SuppressWarnings("unchecked")
    public T get(int index) throws CustomException {
        lock.lock();
        try {
            if (index >= 0 && index < size) {
                return (T) elements[index];
            }
            throw new CustomException("Index out of range");
        } finally {
            lock.unlock();
        }
    }

    public void update(int index, T element) throws CustomException {
        lock.lock();
        try {
            if (index < 0 || index >= size) {
                throw new CustomException("Index out of bounds");
            }
            elements[index] = element;
        } finally {
            lock.unlock();
        }
    }

    public void delete(int index) throws CustomException {
        lock.lock();
        try {
            if (index < 0 || index >= size) {
                throw new CustomException("Index out of bounds");
            }
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
            elements[--size] = null;
        } finally {
            lock.unlock();
        }
    }
}