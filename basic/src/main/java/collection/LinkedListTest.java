package collection;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedListTest {

    private static List<String> linkList = new LinkedList<String>();
    private static List<String> arrayList = new ArrayList<String>();
    private static final int total = 100000;

    public static void main(String[] args) {
        insertArrayList();
        insertLinkList();
    }

    static void insertLinkList() {
        long b = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            //linkList.add("test" + i);
            //linkList.add(0, "test"+i);  // 第一位
            //linkList.add(linkList.size() % 2, "test"+i);  // 中间一位
            linkList.add(linkList.size() ,"test" + i); // 最后一位
        }
        long e = System.currentTimeMillis();
        System.out.println("LinkedList time is " + (e - b) + " size is " + linkList.size());
    }

    static void insertArrayList() {
        long b1 = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
           // arrayList.add("test" + i);
           // arrayList.add(0 ,"test" + i); // 第一位
            //arrayList.add(arrayList.size() % 2, "test" + i);
            arrayList.add(arrayList.size() ,"test" + i); // 最后一位
        }
        long e1 = System.currentTimeMillis();
        System.out.println("ArrayList time is " + (e1 - b1) + " size is " + arrayList.size());
    }
}


/**
 *
 * 
 * 10w 第一位插入
 * ArrayList time is 1342 size is 100000
 * LinkedList time is 43 size is 100000
 *
 * 10w 中间插入
 * ArrayList time is 1477 size is 100000
 * LinkedList time is 47 size is 100000
 *
 * 最后一位插入
 * ArrayList time is 59 size is 100000
 * LinkedList time is 25 size is 100000
 *
 **/