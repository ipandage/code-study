/***
 * Excerpted from "Programming Concurrency on the JVM",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/vspcon for more book information.
 ***/
package gao.study.concurrency.jvm.tamingSharedMutability.enhanceconcurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class EnergySource {
    private final long MAXLEVEL = 100;
    private final AtomicLong level = new AtomicLong(MAXLEVEL);
    private static final ScheduledExecutorService replenishTimer =
            Executors.newScheduledThreadPool(10);
    private ScheduledFuture<?> replenishTask;

    private EnergySource() {
    }

    private void init() {
        replenishTask = replenishTimer.scheduleAtFixedRate(new Runnable() {
            public void run() {
                replenish();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    public static EnergySource create() {
        final EnergySource energySource = new EnergySource();
        energySource.init();
        return energySource;
    }

    public long getUnitsAvailable() {
        return level.get();
    }

    public boolean useEnergy(final long units) {
        final long currentLevel = level.get();
        if (units > 0 && currentLevel >= units) {
            return level.compareAndSet(currentLevel, currentLevel - units);
        }
        return false;
    }

    public synchronized void stopEnergySource() {
        replenishTask.cancel(false);
    }

    private void replenish() {
        if (level.get() < MAXLEVEL) level.incrementAndGet();
    }
}
