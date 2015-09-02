package concurrency.forkJoinPool;

/**
 * Created with IntelliJ IDEA.
 * User: gaoxingang
 * Date: 14-5-27
 * Time: 下午11:17
 * To change this template use File | Settings | File Templates.
 */
public class TestWhile {
    public static void main(String[] args) {
        System.out.println("111");

        for (int i=0; i<1000; i++) {
            System.out.println(i);
            do;while (i < 100);
            System.out.println(i);
        }
    }
}
