package array;

import java.util.Arrays;

/**
 * Implementation of a resizable array
 * @author csantos
 */
public class ArrayList<T> {

    private int size = 0;
    private int capacity;
    private Object[] elements;

    public ArrayList() {
        this.capacity = 10;
        elements = new Object[capacity];
    }

    public ArrayList(int capacity) {
        this.capacity = capacity;
        elements = new Object[capacity];
    }

    /**
     * Add the given element at the end of the list
     */
    public void add(T element) {
        ensureEnoughCapacity();
        elements[size++] = element;
    }

    /**
     * Add the given element at the given index
     */
    public void add(T element, int index) {
        validateIndex(index);
        ensureEnoughCapacity();

        size++;

        Object toBeInserted = element;
        Object tmp;

        for (int i = index; i < size; i++) {
            tmp = elements[i];
            elements[i] = toBeInserted;
            toBeInserted = tmp;
        }

    }

    /**
     * Changes the value of the element at the given index
     */
    public void set(T newData, int index) {
        validateIndex(index);
        elements[index] = newData;
    }

    private void ensureEnoughCapacity() {
        if(size == capacity) {
            capacity *= 2;
            elements = Arrays.copyOf(elements, capacity);
        }
    }

    /**
     * Retrieves the element of the list at the given index
     */
    public T get(int index) {
        validateIndex(index);
        return (T) elements[index];
    }

    /**
     * Return whether or not this list contains the given value
     */
    public boolean contains(T element) {
        for(int i = 0; i < size; i++) {
            if(elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    private void validateIndex(int index) {
        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Removes the element at the given index
     */
    public void remove(int index) {
        validateIndex(index);
        elements[index] = null;
        rearrangeIndexesFrom(index);

        size--;
    }

    private void rearrangeIndexesFrom(int index) {
        for(int i = index; i < size; i++) {
            elements[i] = elements[i + 1];
        }
    }

    public int size() {
        return size;
    }
}
