package gao.study.concurrency;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * 主要用于在高并发环境下的高效程序处理。使用非阻塞算法来实现并发控制。
 * @author Administrator
 *
 */
public class AtomicIntegerCompareTest {

	private int value;

	public AtomicIntegerCompareTest(int value) {
		this.value = value;
	}

	public synchronized int increase() {
		return value++;
	}

	public static void main(String args[]) {
		long start = System.currentTimeMillis();

		AtomicIntegerCompareTest test = new AtomicIntegerCompareTest(0);
		for (int i = 0; i < 10000000; i++) {
			test.increase();
		}
		long end = System.currentTimeMillis();
		System.out.println("time elapse:" + (end - start));

		long start1 = System.currentTimeMillis();

		AtomicInteger atomic = new AtomicInteger(0);

		for (int i = 0; i < 10000000; i++) {
			atomic.incrementAndGet();
		}
		long end1 = System.currentTimeMillis();
		System.out.println("time elapse:" + (end1 - start1));

	}

}

//time elapse:250
//time elapse:110



