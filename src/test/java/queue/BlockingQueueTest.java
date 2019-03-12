package queue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;

/**
 * Test class for test cases of BlockingQueue implementation
 * @author csantos
 */
public class BlockingQueueTest {

    private BlockingQueue<Integer> blockingQueue;
    private ExecutorService executorService;

    @Before
    public void initialize() {
        blockingQueue = new BlockingQueue<>(1);
        executorService = Executors.newSingleThreadExecutor();
    }

    @After
    public void shutdownExecutorService() {
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    @Test(expected = NullPointerException.class)
    public void nullCannotBeAdded() throws InterruptedException {
        blockingQueue.append(null);
    }

    @Test
    public void cannotInsertMoreElementsIfQueueAlreadyFull() throws InterruptedException {
        executorService.submit(() -> blockingQueue.append(1));
        executorService.submit(() -> blockingQueue.append(2));

        executorService.awaitTermination(2, TimeUnit.SECONDS);
        assertEquals(1, blockingQueue.size());
    }

    @Test(timeout = 3000)
    public void elementWillBeRetrievedWhenQueueNotEmpty() throws InterruptedException, ExecutionException {
        Future<Integer> result = executorService.submit(blockingQueue::poll);
        Thread.sleep(1000);
        blockingQueue.append(1);

        assertEquals(new Integer(1), result.get());
    }

    @Test(timeout = 3000)
    public void elementWillBeAddedWheSpaceIsAvailable() throws InterruptedException, TimeoutException {
        executorService.submit(() -> blockingQueue.append(1));
        executorService.submit(() -> blockingQueue.append(2));

        Thread.sleep(1000);
        blockingQueue.poll();
        Integer result = blockingQueue.poll();

        assertEquals(new Integer(2), result);
    }


}
