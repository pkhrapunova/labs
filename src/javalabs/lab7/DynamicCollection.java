package javalabs.lab7;

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
//no hi
    public void add(T element) {
        if (size == elements.length) {
            increaseCapacity();
        }
        elements[size++] = element;
    }
    private void increaseCapacity() {
        int newCapacity = elements.length * 2;
        elements = Arrays.copyOf(elements, newCapacity);
    }
    @SuppressWarnings("unchecked")
    public T get(int index) throws CustomException {
        if (index >= 0 && index < size) {
            return (T) elements[index];
        }
        throw new CustomException("Index out of range");
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
    


}