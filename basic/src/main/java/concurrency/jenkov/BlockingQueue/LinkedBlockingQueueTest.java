package concurrency.jenkov.BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> unbounded = new LinkedBlockingQueue<String>();
        BlockingQueue<String> bounded   = new LinkedBlockingQueue<String>(1024);

        bounded.put("gao");
        bounded.put("xin");
        bounded.put("gang");
        System.out.println(bounded.take());
        System.out.println(bounded.take());
        System.out.println(bounded.take());
    }


}
