package stack;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Objects;

/**
 * Implementation of stack using inner array
 * @author csantos
 */
public class StackArray<T> {

    private int size;
    private int capacity;
    private Object[] elements;
    private int top = -1;

    public StackArray() {
        size = 0;
        capacity = 10;
        elements = new Object[capacity];
    }

    /**
     * Inserts a new element in the top of the stack
     */
    public void push(T data) {
        Objects.requireNonNull(data);

        if(size == capacity) {
            capacity *= 2;
            elements = Arrays.copyOf(elements, capacity);
        }

        elements[++top] = data;
        size++;
    }

    /**
     * Removes and retrieves the element at the top of the stack
     */
    public T poll() {
        throwExceptionIfTopIsNull();

        T toBeRemoved = (T) elements[top];
        elements[top--] = null;
        size--;

        return toBeRemoved;
    }

    /**
     * Retrieves the element at the top of the stack.
     */
    public T peek() {
        throwExceptionIfTopIsNull();
        return (T) elements[top];
    }

    /**
     * Returns whether the stack contains the given element or not
     */
    public boolean contains(T data) {
        if(data == null) {
            return false;
        }

        for (int i = top; i >= 0; i--) {
            if(elements[i].equals(data)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void throwExceptionIfTopIsNull() {
        if(top == -1) {
            throw new EmptyStackException();
        }
    }
}
