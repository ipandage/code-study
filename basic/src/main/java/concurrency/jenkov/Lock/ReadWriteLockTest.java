package concurrency.jenkov.Lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    public static void main(String[] args) throws InterruptedException {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();


        readWriteLock.readLock().lock();

        // multiple readers can enter this section
        // if not locked for writing, and not writers waiting
        // to lock for writing.

        readWriteLock.readLock().unlock();


        readWriteLock.writeLock().lock();

        // only one writer can enter this section,
        // and only if no threads are currently reading.

        readWriteLock.writeLock().unlock();
    }
}
