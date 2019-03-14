package datastructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for test cases of MinHeap implementation
 * @author csantos
 */
public class MinHeapTest {

    private MinHeap<String> heap;

    @Before
    public void initialize() {
        heap = new MinHeap(10);
    }

    @Test(expected = NullPointerException.class)
    public void nullCannotBeAdded() {
        heap.insert(null);
    }

    @Test
    public void getMinReturnsNullIfHeapIsEmpty() {
        assertNull(heap.getMin());
        assertEquals(0, heap.size());
    }

    @Test
    public void getMinReturnsSmallestValue() {
        heap.insert("A");
        heap.insert("B");
        heap.insert("1");

        assertEquals("1", heap.getMin());
    }

    @Test
    public void extractMinReturnsNullIfHeapIsEmpty() {
        assertNull(heap.extractMin());
    }

    @Test
    public void extractMinReturnsAndRemovesSmallestValue() {
        heap.insert("A");
        heap.insert("0");
        heap.insert("C");
        heap.insert("1");

        assertEquals("0", heap.extractMin());
        assertEquals(3, heap.size());
        assertEquals("1", heap.extractMin());
        assertEquals(2, heap.size());
        assertEquals("A", heap.extractMin());
        assertEquals(1, heap.size());
        assertEquals("C", heap.extractMin());
        assertEquals(0, heap.size());
    }

    @Test
    public void containsReturnTrueIfGivenElementIsPresent() {
        heap.insert("1");
        assertTrue(heap.contains("1"));
    }

    @Test
    public void containsReturnFalseIfGivenElementIsAbsent() {
        heap.insert("1");
        assertFalse(heap.contains("0"));
    }

}
