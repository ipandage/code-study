package gao.study.concurrency;

import java.util.HashMap;
import java.util.UUID;

public class ConcurrentHashMapTest {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		final HashMap<String, String> map = new HashMap<String, String>(2);
		Thread t = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					new Thread(new Runnable() {
						public void run() {
							map.put(UUID.randomUUID().toString(), "");
						}
					}, "ftf" + i).start();
				}
			}
		}, "ftf");
		t.start();
		t.join();
	}

}
