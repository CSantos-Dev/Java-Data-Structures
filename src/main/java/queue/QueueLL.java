package queue;

import java.util.Objects;

/**
 * Implementation of Queue using inner LinkedList
 * @author csantos
 */
public class QueueLL<T> {

    private int size = 0;
    private Node head;
    private Node tail;

    /**
     * Inserts a new non-null element in the queue
     */
    public void append(T data) {
        Objects.requireNonNull(data);
        Node newNode = new Node(data);
        if(head == null) {
            head = tail = newNode;
        }

        tail.next = newNode;
        tail = newNode;
        size++;
    }

    /**
     * Retrieves and remove the head of the queue
     * or throws an exception if the queue is empty
     */
    public T poll() {
        Objects.requireNonNull(head);

        T toBeRemoved = head.data;
        head = head.next;

        if(head == null) {
            tail = null;
        }

        size--;

        return toBeRemoved;
    }

    /**
     * Retrieves the head of the queue or throws an exception if the queue is empty
     */
    public T peek() {
        Objects.requireNonNull(head);
        return head.data;
    }

    /**
     * Returns whether or not the queue contains the given element
     */
    public boolean contains(T element) {
        if (element == null) {
            return false;
        }

        if(head.data.equals(element)) {
            return true;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
            if(current.data.equals(element)) {
                return true;
            }
        }

        return false;
    }


    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    private class Node {
        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
        }
    }
}
