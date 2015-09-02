package concurrency.jenkov.BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new PriorityBlockingQueue();

        queue.put(1);
        queue.put(2);
        queue.put(3);

        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
    }
}
