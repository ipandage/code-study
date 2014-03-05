package gao.study.concurrency;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();

		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		
		List<String> copyList = new CopyOnWriteArrayList<String>();

		copyList.add("1");
		copyList.add("2");
		copyList.add("3");
		copyList.add("4");
		
		
		// 【场景一】对于ArrayList，使用直接方式，一边遍历，一边删除，会报错。
//		for (String item : list) {
//			strs.remove(item);
//		}

		// 解决办法一：使用迭代器，一边遍历，一边删除，不会报错。
//		Iterator<String> it = list.iterator();
//		while (it.hasNext()) {
//			it.next();
//			it.remove();
//		}
//		
//		System.out.println(list.size());
		
		// 解决办法二：使用CopyOnWriteArrayList，直接方式，一边遍历，一会删除，不会报错。
//		for (String item : copyList) {
//			copyList.remove(item);
//		}
//		
//		System.out.println(copyList.size());
		
		// 【场景二】对于ArrayList，使用迭代器，一边遍历，一边add，会报错。
//		Iterator<String> it = list.iterator();
//		while (it.hasNext()) {
//			String str = it.next();
//			String item = str + "...";
//			list.add(item);
//		}
		
		// 解决办法一：改用CopyOnWriteArrayList，直接方式，一边遍历，一边add，不会报错。
//		Iterator<String> it = copyList.iterator();
//		while (it.hasNext()) {
//			String str = it.next();
//			String item = str + "...";
//			copyList.add(item);
//		}
//		System.out.println(copyList.size());
		
		// 【场景三】对于CopyOnWriteArrayList，迭代器，不能remove。
//		Iterator<String> it = copyList.iterator();
//		while (it.hasNext()) {
//			it.next();
//			it.remove();
//		}
		
		// 解决办法一：使用直接方式，一边遍历，一边add/remove()
		
	}

}
