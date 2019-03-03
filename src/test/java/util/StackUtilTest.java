package util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import stack.StackLL;

import java.util.stream.IntStream;

/**
 * @author csantos
 */
public class StackUtilTest {

    private StackLL<Integer> stack;

    @Before
    public void initializeBeforeEachTest() {
        stack = new StackLL<>();
    }

    @Test
    public void testReverse() {
        insertDataInStack();
        StackUtil.reverse(stack);

        assertOrderIsRight();
    }

    @Test
    public void testReverseByRecursion() {
        insertDataInStack();
        StackUtil.reverseByRecursion(stack);

        assertOrderIsRight();
    }

    private void assertOrderIsRight() {
        Assert.assertEquals( Integer.valueOf(1), stack.poll());
        Assert.assertEquals(Integer.valueOf(2), stack.poll());
        Assert.assertEquals( Integer.valueOf(3), stack.poll());
        Assert.assertEquals(Integer.valueOf(4), stack.poll());
        Assert.assertEquals(Integer.valueOf(5), stack.poll());
    }

    private void insertDataInStack() {
        IntStream.rangeClosed(1, 5).forEach(stack::push);
    }

}
