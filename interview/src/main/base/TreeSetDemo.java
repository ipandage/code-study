package base;

import java.util.TreeSet;

public class TreeSetDemo {

    public static void main(String[] args)
    {
        TreeSet ts = new TreeSet();

        ts.add(30);
        ts.add(40);
        ts.add(20);
        System.out.println(ts.first());

        int next = 50;
        int first = (Integer)ts.first();
        if (next > first) {
            ts.remove(first);
            ts.add(next);
        }

        System.out.println(ts);
    }
}
