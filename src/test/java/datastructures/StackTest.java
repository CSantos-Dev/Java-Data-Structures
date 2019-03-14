package datastructures;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.*;

/**
 * Test class for test cases of StackLL and StackArray implementations
 * @author csantos
 */
public class StackTest {

    private StackLL<String> stackLL;
    private StackArray<String> stackArray;

    @Before
    public void initializeBeforeEachTest() {
        stackLL = new StackLL<>();
        stackArray = new StackArray<>();
    }

    @Test(expected = EmptyStackException.class)
    public void popThrowExceptionIfStackIsEmpty_StackLL() {
        stackLL.poll();
    }

    @Test(expected = EmptyStackException.class)
    public void popThrowExceptionIfStackIsEmpty_StackArray() {
        stackArray.poll();
    }

    @Test(expected = EmptyStackException.class)
    public void peekThrowExceptionIfStackIsEmpty_StackLL() {
        stackLL.poll();
    }

    @Test(expected = EmptyStackException.class)
    public void peekThrowExceptionIfStackIsEmpty_StackArray() {
        stackArray.poll();
    }

    @Test
    public void popReturnsAndRemoveTopElementIfNotEmpty_StackLL() {
        stackLL.push("1");
        String top = stackLL.poll();

        assertEquals("1", top);
        assertTrue(stackLL.isEmpty());
    }

    @Test
    public void popReturnsAndRemoveTopElementIfNotEmpty_StackArray() {
        stackArray.push("1");
        String top = stackArray.poll();

        assertEquals("1", top);
        assertTrue(stackArray.isEmpty());
    }

    @Test
    public void peekReturnsTopElementIfNotEmpty_StackLL() {
        stackLL.push("1");
        String top = stackLL.peek();

        assertEquals("1", top);
        assertFalse(stackLL.isEmpty());
    }

    @Test
    public void peekReturnsTopElementIfNotEmpty_StackArray() {
        stackArray.push("1");
        String top = stackArray.peek();

        assertEquals("1", top);
        assertFalse(stackArray.isEmpty());
    }

    @Test
    public void elementsAreInsertedCorrectly_StackLL() {
        stackLL.push("1");
        stackLL.push("2");
        stackLL.push("3");

        Assert.assertEquals("3", stackLL.poll());
        Assert.assertEquals("2", stackLL.poll());
        Assert.assertEquals("1", stackLL.poll());
    }

    @Test
    public void elementsAreInsertedCorrectly_StackArray() {
        stackArray.push("1");
        stackArray.push("2");
        stackArray.push("3");

        Assert.assertEquals("3", stackArray.poll());
        Assert.assertEquals("2", stackArray.poll());
        Assert.assertEquals("1", stackArray.poll());
    }

    @Test(expected = NullPointerException.class)
    public void insertNullThrowsException_StackLL() {
        stackLL.push(null);
    }

    @Test(expected = NullPointerException.class)
    public void insertNullThrowsException_StackArray() {
        stackArray.push(null);
    }

    @Test
    public void sizeCorrectlyUpdatedForEachOperation_StackLL() {
        stackLL.push("1");
        Assert.assertEquals(1, stackLL.size());

        stackLL.push("2");
        Assert.assertEquals(2, stackLL.size());

        stackLL.push("2");
        Assert.assertEquals(3, stackLL.size());

        stackLL.poll();
        Assert.assertEquals(2, stackLL.size());
    }

    @Test
    public void sizeCorrectlyUpdatedForEachOperation_StackArray() {
        stackArray.push("1");
        Assert.assertEquals(1, stackArray.size());

        stackArray.push("2");
        Assert.assertEquals(2, stackArray.size());

        stackArray.push("2");
        Assert.assertEquals(3, stackArray.size());

        stackArray.poll();
        Assert.assertEquals(2, stackArray.size());
    }

    @Test
    public void containsReturnsTrueIfGivenElementExistInTheStack_StackLL() {
        stackLL.push("1");
        stackLL.push("3");
        stackLL.push("5");
        assertTrue(stackLL.contains("3"));
    }

    @Test
    public void containsReturnsTrueIfGivenElementExistInTheStack_StackArray() {
        stackArray.push("1");
        stackArray.push("3");
        stackArray.push("5");
        assertTrue(stackArray.contains("3"));
    }


    @Test
    public void containsReturnsFalseIfGivenElementDoesNotExistInTheStack_StackLL() {
        stackLL.push("1");
        assertFalse(stackLL.contains("3"));
    }

    @Test
    public void containsReturnsFalseIfGivenElementDoesNotExistInTheStack_StackArray() {
        stackArray.push("1");
        assertFalse(stackArray.contains("3"));
    }
}
