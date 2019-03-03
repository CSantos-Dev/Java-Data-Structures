package util;

import stack.StackLL;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * Utility methods for stack.Stack
 * @author csantos
 */
public class StackUtil {

    /**
     * Reverses the given stack using a queue
     */
    public static <T> void reverse(StackLL<T> stack) {
        Queue<T> queue = new LinkedList<T>();
        while (!stack.isEmpty()) {
            queue.offer(stack.poll());
        }

        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }
    }

    /**
     * Alternative method to reverse the stack using recursion
     */
    public static <T> void reverseByRecursion(StackLL<T> stack) {
        if(!stack.isEmpty()) {
            T tmp = stack.poll();
            reverseByRecursion(stack);
            insertAtBottom(stack, tmp);
        }
    }

    private static <T> void insertAtBottom(StackLL<T> stack, T element) {
        if(stack.isEmpty()) {
            stack.push(element);
        } else {
            T tmp = stack.poll();
            insertAtBottom(stack, element);
            stack.push(tmp);
        }
    }
}
