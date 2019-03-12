package queue;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * Simple implementation of bounded BlockingQueue data structure.
 * @author csantos
 */
public class BlockingQueue<T> {

    private Queue<T> queue = new LinkedList<>();
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Add the given element at the end of the queue.
     * A NullPointerException will be thrown if null element is added
     * If the queue is already full, it will wait until it will be enough space.
     */
    public synchronized void append(T data) {
        Objects.requireNonNull(data);
        while (queue.size() == capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.offer(data);
        notify();
    }

    /**
     * Retrieves and removes the head of the queue.
     * If the queue is empty, it will wait until a new element is available
     */
    public synchronized T poll() {
        while (queue.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        T value = queue.poll();
        notify();
        return value;
    }

    public int size() {
        return queue.size();
    }

}
