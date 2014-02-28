package gao.study.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 主要用于在高并发环境下的高效程序处理。使用非阻塞算法来实现并发控制。
 * @author Administrator
 *
 */
public class AtomicIntegerCompareTest {

	public static class Calculator {
		int i = 0;
		public void increase() {
			System.out.println("increase before " + i);
			i++;
			System.out.println("increase after " + i);
		}
		public void reduce() {
			System.out.println("reduce before " + i);
			i--;
			System.out.println("reduce after " + i);
		}
		
	}
	
	/**
	 * int ++ -- 线程安全测试
	 */
	public static void testCalculator() {
		final Calculator calculator = new Calculator();
		class Increaser implements Runnable {
			public void run() {
				while (true) {
					calculator.increase();
				}
			}
		}
		
		class Reducer implements Runnable {
			public void run() {
				while (true) {
					calculator.reduce();
				}
			}
		}
		
		ExecutorService service = Executors.newCachedThreadPool();

		Increaser increaser = new Increaser();

		Reducer reducer = new Reducer();

		service.submit(increaser);

		service.submit(reducer);

		// 程序运行5s后,所有任务停止

		try {

			Thread.sleep(10000);

		} catch (InterruptedException ex) {

		}

		service.shutdownNow();
	}
	
	
	private int value;

	public AtomicIntegerCompareTest(int value) {
		this.value = value;
	}

	public synchronized int increase() {
		return value++;
	}

	public static void main(String args[]) {
		
		AtomicIntegerCompareTest.testCalculator();
		
//		long start = System.currentTimeMillis();
//
//		AtomicIntegerCompareTest test = new AtomicIntegerCompareTest(0);
//		for (int i = 0; i < 10000000; i++) {
//			test.increase();
//		}
//		long end = System.currentTimeMillis();
//		System.out.println("time elapse:" + (end - start));
//
//		long start1 = System.currentTimeMillis();
//
//		AtomicInteger atomic = new AtomicInteger(0);
//
//		for (int i = 0; i < 10000000; i++) {
//			atomic.incrementAndGet();
//		}
//		long end1 = System.currentTimeMillis();
//		System.out.println("time elapse:" + (end1 - start1));

	}

}

//time elapse:250
//time elapse:110



