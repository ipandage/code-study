package gao.study.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

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
					System.out.println(nextInt);
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
		List safeList = new CopyOnWriteArrayList(); // 也可以换成new
																		// CopyToWriteArrayList
		final int N = 10000;
		for (int i = 0; i < 10; i++) {
			unsafeList.clear();
			safeList.clear();
			int unsafeSize = demo(unsafeList, N);
			int safeSize = demo(safeList, N);
			System.out.println("unsafe/safe: " + unsafeSize + "/" + safeSize);
		}
	}

}
