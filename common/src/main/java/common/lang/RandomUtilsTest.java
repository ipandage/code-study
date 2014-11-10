package common.lang;

import org.apache.commons.lang.math.RandomUtils;

/**
 * Created with IntelliJ IDEA.
 * User: gaoxingang
 * Date: 14-11-10
 * Time: 下午9:27
 * To change this template use File | Settings | File Templates.
 */
public class RandomUtilsTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println("RandomUtils.nextInt(10) = " + RandomUtils.nextInt(10));
            // System.out.println("RandomUtils.nextBoolean() = " + RandomUtils.nextBoolean());
            // System.out.println("RandomUtils.nextBoolean() = " + RandomUtils.nextDouble());
        }
        
    }
}
