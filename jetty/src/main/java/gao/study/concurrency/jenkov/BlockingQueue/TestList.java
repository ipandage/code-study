package gao.study.concurrency.jenkov.BlockingQueue;

import java.util.ArrayList;
import java.util.List;

public class TestList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        List list1 = list.subList(0, 2);
        List list2 = list.subList(2, 5);
        System.out.println(list1);
        System.out.println(list2);

        int sz = list.size();
        int middleSz = sz/2;
        System.out.println(middleSz);

        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println(availableProcessors);
    }
}
