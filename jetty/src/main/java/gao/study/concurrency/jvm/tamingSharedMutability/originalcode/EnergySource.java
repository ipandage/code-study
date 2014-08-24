/***
 * Excerpted from "Programming Concurrency on the JVM",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/vspcon for more book information.
 ***/
package gao.study.concurrency.jvm.tamingSharedMutability.originalcode;

//Bad code
public class EnergySource {
    private final long MAXLEVEL = 100;
    private long level = MAXLEVEL;
    private boolean keepRunning = true;

    public EnergySource() {
        new Thread(new Runnable() {
            public void run() {
                replenish();
            }
        }).start();
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
            if (level < MAXLEVEL) level++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }
}
