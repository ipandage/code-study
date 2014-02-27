package gao.study.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
