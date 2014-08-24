/***
 * Excerpted from "Programming Concurrency on the JVM",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/vspcon for more book information.
 ***/
package gao.study.concurrency.jvm.tamingSharedMutability.fixingconstructor;

public class EnergySource {
    private final long MAXLEVEL = 100;
    private long level = MAXLEVEL;
    private boolean keepRunning = true;

    //Fixing constructor...other issues pending
    private EnergySource() {
    }

    private void init() {
        new Thread(new Runnable() {
            public void run() {
                replenish();
            }
        }).start();
    }

    public static EnergySource create() {
        final EnergySource energySource = new EnergySource();
        energySource.init();
        return energySource;
    }

    public long getUnitsAvailable() {
        return level;
    }

    public boolean useEnergy(final long units) {
        if (units > 0 && level >= units) {
            level -= units;
            return true;
        }
        return false;
    }

    public void stopEnergySource() {
        keepRunning = false;
    }

    private void replenish() {
        while (keepRunning) {
            System.out.println("111");
            if (level < MAXLEVEL) level++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }

    public static void main(String[] args) {
        EnergySource.create();
    }
}
