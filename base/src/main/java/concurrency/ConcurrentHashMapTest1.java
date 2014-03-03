package concurrency;

import java.util.HashMap;
import java.util.UUID;

public class ConcurrentHashMapTest1 {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// http://www.infoq.com/cn/articles/ConcurrentHashMap
		// 因为多线程环境下，使用HashMap进行put操作会引起死循环，导致CPU利用率接近100%，所以在并发情况下不能使用HashMap，如以下代码
		final HashMap<String, String> map = new HashMap<String, String>(2);
		Thread t = new Thread(new Runnable() {
		    public void run() {
		        for (int i = 0; i < 1000000; i++) {
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
