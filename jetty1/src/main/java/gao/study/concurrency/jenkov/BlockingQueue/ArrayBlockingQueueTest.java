package gao.study.concurrency.jenkov.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new ArrayBlockingQueue(1024);

        queue.put("1");
        queue.put("2");
        queue.put("3");

        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());

    }
}
