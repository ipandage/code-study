package gao.study.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * http://www.cnblogs.com/likwo/archive/2010/07/01/1769199.html
 * 
 *         本例介绍一个特殊的队列:BlockingQueue,如果BlockQueue是空的,从BlockingQueue取东西的操作将会被阻断进入等待状态
 *         ,直到BlockingQueue进了东西才会被唤醒.同样,如果BlockingQueue是满的,
 *         任何试图往里存东西的操作也会被阻断进入等待状态,直到BlockingQueue里有空间才会被唤醒继续操作.
 */
public class BlockingQueueTest {

	/** 定义装苹果的篮子 */

	public static class Basket {

		// 篮子,能够容纳3个苹果

		BlockingQueue<String> basket = new ArrayBlockingQueue<String>(3);

		// 生产苹果,放入篮子

		public void produce() throws InterruptedException {

			// put方法放入一个苹果,若basket满了,等到basket有位置

			basket.put("An apple");

		}

		// 消费苹果,从篮子中取走

		public String consume() throws InterruptedException {

			// take方法取出一个苹果,若basket为空,等到basket有苹果为止

			return basket.take();

		}

	}

	// 测试方法

	public static void testBasket() {

		final Basket basket = new Basket();// 建立一个装苹果的篮子

		// 定义苹果生产者

		class Producer implements Runnable {

			public void run() {

				try {

					while (true) {

						// 生产苹果

						System.out.println("生产者准备生产苹果: "
								+ System.currentTimeMillis());

						basket.produce();

						System.out.println("生产者生产苹果完毕: "
								+ System.currentTimeMillis());

						// 休眠300ms

						Thread.sleep(300);

					}

				} catch (InterruptedException ex) {

				}

			}

		}

		// 定义苹果消费者

		class Consumer implements Runnable {

			public void run() {

				try {

					while (true) {

						// 消费苹果

						System.out.println("消费者准备消费苹果: "
								+ System.currentTimeMillis());

						basket.consume();

						System.out.println("消费者消费苹果完毕: "
								+ System.currentTimeMillis());

						// 休眠1000ms

						Thread.sleep(1000);

					}

				} catch (InterruptedException ex) {

				}

			}

		}

		ExecutorService service = Executors.newCachedThreadPool();

		Producer producer = new Producer();

		Consumer consumer = new Consumer();

		service.submit(producer);

		service.submit(consumer);

		// 程序运行5s后,所有任务停止

		try {

			Thread.sleep(100000);

		} catch (InterruptedException ex) {

		}

		service.shutdownNow();

	}

	public static void main(String[] args) {

		BlockingQueueTest.testBasket();

	}

}
