package datastructures;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Test class for BinarySearchTree test cases
 * @author csantos
 */
public class BinarySearchTreeTest {

    private BinarySearchTree bst;

    @Before
    public void initializeTree() {
        bst = new BinarySearchTree();
    }

    @Test(expected = NullPointerException.class)
    public void nullElementCannotBeAdded() {
        bst.add(null);
    }

    @Test
    public void duplicatedElementsNotStored() {
        bst.add(1);
        bst.add(1);
        bst.add(1);

        assertEquals(1, bst.size());
    }

    @Test
    public void containsReturnsTrueIfTreeContainsElement() {
        bst.add("A");
        bst.add("B");
        bst.add("1");
        bst.add("2");
        bst.add("3");

        assertTrue(bst.contains("1"));
        assertTrue(bst.contains("2"));
        assertTrue(bst.contains("3"));
        assertTrue(bst.contains("A"));
        assertTrue(bst.contains("B"));
    }

    @Test
    public void containsReturnsFalseIfElementDoesNotExist() {
        bst.add("A");
        assertFalse(bst.contains("1"));
    }

    @Test
    public void minReturnsSmallestValue() {
        IntStream.rangeClosed(1, 10).forEach(bst::add);
        assertEquals(1, bst.min());
    }

    @Test
    public void maxReturnsSmallestValue() {
        IntStream.rangeClosed(1, 10).forEach(bst::add);
        assertEquals(10, bst.max());
    }

    @Test
    public void testRemoveWhenNodeHasNoChildren() {
        bst.add(1);
        assertTrue(bst.contains(1));
        assertEquals(1, bst.size());

        bst.remove(1);
        assertFalse(bst.contains(1));
        assertEquals(0, bst.size());
    }

    @Test
    public void testRemoveWhenNodeHasOneChild() {
        bst.add(1);
        bst.add(2);
        assertTrue(bst.contains(1));
        assertEquals(2, bst.size());

        bst.remove(1);
        assertFalse(bst.contains(1));
        assertTrue(bst.contains(2));
        assertEquals(1, bst.size());
    }

    @Test
    public void testRemoveWhenNodeHasBothChildren() {
        bst.add(1);
        bst.add(2);
        bst.add(0);
        assertTrue(bst.contains(1));
        assertEquals(3, bst.size());

        bst.remove(1);
        assertFalse(bst.contains(1));
        assertTrue(bst.contains(0));
        assertTrue(bst.contains(2));
        assertEquals(2, bst.size());
    }

    @Test
    public void removeDoesNothingWhenElementNotPresent() {
        bst.add(1);
        bst.remove(2);

        assertTrue(bst.contains(1));
        assertEquals(1, bst.size());
    }

    @Test
    public void sizeCorrectlyUpdated() {
        bst.add(1);
        bst.add(-1);
        bst.add(5);

        assertEquals(3, bst.size());

        bst.remove(4);
        bst.remove(2);
        bst.remove(-1);
        bst.add(3);
        bst.remove(1);

        assertEquals(2, bst.size());
    }
}
