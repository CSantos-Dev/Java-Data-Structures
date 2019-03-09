package map;

import java.util.Arrays;

/**
 * Implementation of HashMap.
 * hashcode() method is used to allocate the entries within the inner array of LinkedList
 * @author csantos
 */
public class HashMap<K, V> {

    private int size;
    private int capacity;
    private Entry[] entries;

    public HashMap() {
        size = 0;
        capacity = 10;
        entries = new Entry[capacity];
    }

    /**
     * Inserts a new key-value pair in the map
     */
    public void put(K key, V value) {
        if(size == capacity) {
            increaseCapacity();
        }

        int index = mapKeyToIndex(key);
        Entry newEntry = new Entry(key, value);

        // In case there are no entries for the generated index, a new entry will be allocated there and no more checks are needed
        if(entries[index] == null) {
            entries[index] = newEntry;
            size++;
        } else {
            // If there are already entries, and it exists an entry with the given key, then we overwrite the value
            Entry current = entries[index];

            if (current.key.equals(key)) {
                current.value = value;
                return;
            }

            while (current.next != null) {
                current = current.next;
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
            }

            // In case there is no entry with the given key, the a new entry will be allocated at the tail of the Entry LinkedList
            current.next = newEntry;
            size++;
        }
    }

    /**
     * Returns the value associated to the given key or null if it does not exist an entry with the given key
     */
    public V get(K key) {
        int index = mapKeyToIndex(key);
        Entry current = entries[index];

        while (current!= null) {
            if(current.key.equals(key)) {
                return (V) current.value;
            }

            current = current.next;
        }

        return null;
    }

    /**
     * Removes the value associated to the given key and returns true if the operation was successful
     */
    public boolean remove(K key) {
        int index = mapKeyToIndex(key);
        Entry current = entries[index];
        Entry previous = null;

        if(current == null) {
            // No elements
            return false;
        }

        while (current != null) {
            if(current.key.equals(key)) {
                if (previous == null) {
                    entries[index] = current.next;
                    size--;
                    return true;
                } else {
                    previous.next = current.next;
                    size--;
                    return true;
                }
            }

            previous = current;
            current = current.next;
        }

        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * Maps the given key to an index of the inner array
     */
    private int mapKeyToIndex(K key) {
        return key.hashCode() % capacity;
    }

    /**
     * When the maximum initial capacity is reached, it will be multiplied by 2
     */
    private void increaseCapacity() {
        capacity *= 2;
        entries = Arrays.copyOf(entries, capacity);
    }

    private class Entry<K, V> {

        private Entry next;
        private K key;
        private V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
