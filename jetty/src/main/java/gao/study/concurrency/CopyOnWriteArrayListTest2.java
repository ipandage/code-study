package gao.study.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 * ArrayList 是 非线程安全的， CopyOnWriteArrayList
 * 是一个线程安全，并且在读操作时无锁的ArrayList,且适合并发访问。对于集合元素数为10000
 * ，线程数量为100的情况下进行性能测试，随着元素数量和线程数量的增加
 * ，CopyOnWriteArrayList在增加元素和删除元素时的性能下降非常明显，并且性能会比ArrayList低
 * 。但在查找元素这点上随着线程数的增长，性能较ArrayList会好很多。
 * 
 */
public class CopyOnWriteArrayListTest2 {
	public static int demo(final List list, final int testCount)
			throws InterruptedException {
		ThreadGroup group = new ThreadGroup(list.getClass().getName() + "@"
				+ list.hashCode());
		final Random rand = new Random();

		Runnable listAppender = new Runnable() { // 这里面实现了Runnable接口类，覆盖了它的run方法
			public void run() {
				try {
					int nextInt = rand.nextInt(2);
					// System.out.println(nextInt);
					Thread.sleep(nextInt);
				} catch (InterruptedException e) {
					return;
				}
				list.add("0");
			}
		};

		for (int i = 0; i < testCount; i++) {
			new Thread(group, listAppender, "InsertList-" + i).start();
		}

		while (group.activeCount() > 0) {
			Thread.sleep(10);
		}

		return list.size();
	}

	public static void main(String[] args) throws InterruptedException {
		List unsafeList = new ArrayList();
		List safeList = new CopyOnWriteArrayList(); // Collections.synchronizedList(new
													// ArrayList());

		final int N = 100000;
		for (int i = 0; i < 10; i++) {
			unsafeList.clear();
			safeList.clear();

			long curTime = System.nanoTime();
			int unsafeSize = demo(unsafeList, N);
			long duration = System.nanoTime() - curTime;
			System.out.format("Duration: %.2f\n", duration / 1.0e9);

			long _curTime = System.nanoTime();
			int safeSize = demo(safeList, N);
			long _duration = System.nanoTime() - _curTime;
			System.out.format("Duration: %.2f\n", _duration / 1.0e9);

			System.out.println("unsafe/safe: " + unsafeSize + "/" + safeSize);
		}
	}

}
