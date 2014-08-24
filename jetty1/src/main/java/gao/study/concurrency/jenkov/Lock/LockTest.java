package gao.study.concurrency.jenkov.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();

        lock.lock();

        //critical section

        lock.unlock();
    }
}
