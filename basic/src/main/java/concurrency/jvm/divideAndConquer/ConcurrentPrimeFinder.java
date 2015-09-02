/***
 * Excerpted from "Programming Concurrency on the JVM",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/vspcon for more book information.
 ***/
package concurrency.jvm.divideAndConquer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrentPrimeFinder extends AbstractPrimeFinder {
    private final int poolSize;
    private final int numberOfParts;

    public ConcurrentPrimeFinder(final int thePoolSize,
                                 final int theNumberOfParts) {
        poolSize = thePoolSize;
        numberOfParts = theNumberOfParts;
    }

    public int countPrimes(final int number) {
        int count = 0;
        try {
            final List<Callable<Integer>> partitions = new ArrayList<Callable<Integer>>();
            final int chunksPerPartition = number / numberOfParts;
            for (int i = 0; i < numberOfParts; i++) {
                final int lower = (i * chunksPerPartition) + 1;
                final int upper = (i == numberOfParts - 1) ? number : lower + chunksPerPartition - 1;
                partitions.add(new Callable<Integer>() {
                    public Integer call() {
                        return countPrimesInRange(lower, upper);
                    }
                });
            }
            final ExecutorService executorPool = Executors.newFixedThreadPool(poolSize);
            final List<Future<Integer>> resultFromParts = executorPool.invokeAll(partitions, 10000, TimeUnit.SECONDS);
            executorPool.shutdown();
            for (final Future<Integer> result : resultFromParts)
                count += result.get();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return count;
    }

    public static void main(final String[] args) {
        if (args.length < 3)
            System.out.println("Usage: number poolsize numberOfParts");
        else
            new ConcurrentPrimeFinder(Integer.parseInt(args[1]), Integer.parseInt(args[2])).timeAndCompute(Integer.parseInt(args[0]));
    }
}
