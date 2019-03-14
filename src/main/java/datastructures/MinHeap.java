package datastructures;

import java.util.Arrays;
import java.util.Objects;

/**
 * Simple implementation of min heap
 * @author csantos
 */
public class MinHeap<T extends Comparable> {

    private int size;
    private int capacity;
    private T[] heap;

    public MinHeap(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.heap = (T[]) new Comparable[capacity];
    }

    /**
     * Inserts the given element into this queue
     */
    public void insert(T data) {
        Objects.requireNonNull(data);
        ensureEnoughCapacity();

        heap[++size] = data;

        int currentPosition = size;
        int parentPosition = getParentIndex(currentPosition);

        T current = heap[currentPosition];
        T parent = heap[parentPosition];

        while (hasParent(currentPosition) && current.compareTo(parent) < 0) {
            swap(currentPosition, parentPosition);
            currentPosition = getParentIndex(currentPosition);
        }

    }

    /**
     * Retrieves but does not remove the minimum value of the heap or returns null
     * if the heap is empty
     */
    public T getMin() {
        if(size == 0) {
            return null;
        }

        return heap[1];
    }

    /**
     * Removes and retrieves the minimum value of the heap or returns null
     * if the heap is empty
     */
    public T extractMin() {
        if (size == 0) {
            return null;
        }

        T min = heap[1];

        swap(1, size);
        heap[size--] = null;
        rearrangeIndexes();

        return min;
    }

    /**
     * Returns whether the heap contains the given element or not
     */
    public boolean contains(T data){
        for (int i = 1; i <= size; i++) {
            if(heap[i].equals(data)) {
                return true;
            }
        }

        return false;
    }

    private void rearrangeIndexes() {
        int minIndex = 1;

        while (hasLeftChild(minIndex)) {
            int smallerChildIndex = getLeftChildIndex(minIndex);
            T leftChild = heap[smallerChildIndex];

            if(hasRightChild(minIndex) && leftChild.compareTo(heap[getRightChildIndex(minIndex)]) > 0) {
                smallerChildIndex = getRightChildIndex(minIndex);
            }
            if(heap[minIndex].compareTo(heap[smallerChildIndex]) > 0) {
                swap(minIndex, smallerChildIndex);
                minIndex = smallerChildIndex;
            } else {
                break;
            }
        }
    }


    private void swap(int positionOne, int positionTwo) {
        T tmp = heap[positionOne];
        heap[positionOne] = heap[positionTwo];
        heap[positionTwo] = tmp;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) <= size;
    }

    private int getRightChildIndex(int index) {
        return (index * 2) + 1;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) <= size;
    }

    private int getLeftChildIndex(int index) {
        return index * 2;
    }

    private boolean hasParent(int index) {
        return index > 1;
    }

    private int getParentIndex(int index) {
        return index / 2;
    }

    private void ensureEnoughCapacity() {
        if(size == capacity) {
            capacity *= 2;
            heap = Arrays.copyOf(heap, capacity);
        }
    }

    public int size() {
        return size;
    }
}
