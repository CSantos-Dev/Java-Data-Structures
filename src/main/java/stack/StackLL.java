package stack;

import java.util.EmptyStackException;

/**
 * Implementation of stack using inner LinkedList
 * @author csantos
 */
public class StackLL<T> {

    private int size = 0;
    private Node top;

    /**
     * Inserts a new element in the top of the stack
     */
    public void push(T data) {
        if(data == null) {
            throw new NullPointerException("Null value can not be inserted");
        }
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    /**
     * Removes and retrieves the element at the top of the stack
     */
    public T poll() {
        throwExceptionIfTopIsNull();

        T toBeRemoved = top.data;
        top = top.next;
        size--;

        return toBeRemoved;
    }

    /**
     * Retrieves the element at the top of the stack.
     */
    public T peek() {
        throwExceptionIfTopIsNull();
        return top.data;
    }

    /**
     * Returns whether the stack contains the given element or not
     */
    public boolean contains(T element) {
        if(element == null) {
            return false;
        }

        if(top.data.equals(element)) {
            return true;
        }

        Node current = top;
        while (current.next != null) {
            current = current.next;
            if(current.data.equals(element)) {
                return true;
            }
        }

        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void throwExceptionIfTopIsNull() {
        if (top == null) {
            throw new EmptyStackException();
        }
    }

    private class Node {
        private Node next;
        private T data;

        Node(T data) {
            this.data = data;
        }
    }
}
