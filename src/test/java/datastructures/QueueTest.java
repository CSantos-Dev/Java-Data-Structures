package datastructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for test cases of Queue implementation
 * @author csantos
 */
public class QueueTest {

    private QueueLL<String> queueLL;

    @Before
    public void initializeForEachTest() {
        queueLL = new QueueLL<>();
    }

    @Test(expected = NullPointerException.class)
    public void popThrowExceptionIfQueueIsEmpty() {
        queueLL.poll();
    }

    @Test(expected = NullPointerException.class)
    public void peekThrowExceptionIfQueueIsEmpty() {
        queueLL.peek();
    }

    @Test
    public void popReturnsAndRemoveTheFirstElementInserted() {
        queueLL.append("1");
        queueLL.append("2");
        queueLL.append("3");

        assertEquals(3, queueLL.size());
        assertEquals("1", queueLL.poll());
        assertEquals(2, queueLL.size());
    }

    @Test
    public void peekReturnsTheFirstElementInserted() {
        queueLL.append("1");
        queueLL.append("2");
        queueLL.append("3");

        assertEquals(3, queueLL.size());
        assertEquals("1", queueLL.peek());
        assertEquals(3, queueLL.size());
    }

    @Test(expected = NullPointerException.class)
    public void appendThrowsExceptionIfNullIsInserted() {
        queueLL.append(null);
    }

    @Test
    public void sizeCorrectlyUpdatedDuringAllOperations() {
        assertTrue(queueLL.isEmpty());
        queueLL.append("1");
        assertEquals(1, queueLL.size());
        queueLL.append("2");
        assertEquals(2, queueLL.size());
        queueLL.append("3");
        assertEquals(3, queueLL.size());

        queueLL.poll();
        assertEquals(2, queueLL.size());
        queueLL.poll();
        assertEquals(1, queueLL.size());
        queueLL.peek();
        assertEquals(1, queueLL.size());
        assertFalse(queueLL.isEmpty());
    }

    @Test
    public void containsReturnsTrueIfGivenElementExistsInQueue() {
        queueLL.append("1");
        queueLL.append("2");

        assertTrue(queueLL.contains("2"));
    }

    @Test
    public void containsReturnsFalseIfGivenElementExistsInQueue() {
        queueLL.append("1");
        queueLL.append("2");

        assertFalse(queueLL.contains("3"));
    }
}
