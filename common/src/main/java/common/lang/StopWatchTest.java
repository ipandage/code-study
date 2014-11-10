package common.lang;

import org.apache.commons.lang.time.StopWatch;

/**
 * Created with IntelliJ IDEA.
 * User: gaoxingang
 * Date: 14-11-10
 * Time: 下午10:02
 * To change this template use File | Settings | File Templates.
 */

// http://xuliangyong.iteye.com/blog/2008246

public class StopWatchTest {
    public static void main(String[] args) throws InterruptedException {
        StopWatch stWatch = new StopWatch();
        stWatch.start();

        Thread.sleep(1000);

        stWatch.split();

        System.out.println("Time consumed by step1: "+stWatch.getSplitTime());

        Thread.sleep(1000);

        stWatch.split();

        System.out.println("Time consumed by step1 and step2: "+stWatch.getSplitTime());

        stWatch.stop();
    }
}
