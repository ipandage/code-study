package collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListTest {
    public static void remove1(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.equals("bb")) {
                list.remove(s);
            }
        }
    }

    public static void remove1_resolve(ArrayList<String> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            String s = list.get(i);
            if (s.equals("bb")) {
                list.remove(s);
            }
        }
    }

    public static void remove2(ArrayList<String> list) {
        for (String s : list) {
            if (s.equals("bb")) {
                list.remove(s);
            }
        }
    }

    public static void remove2_resolve(ArrayList<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String s = it.next();
            if (s.equals("bb")) {
                it.remove();
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("a");
        list.add("bb");
        list.add("bb");
        list.add("ccc");
        list.add("ccc");
        list.add("ccc");

        remove1(list);

        for (String s : list) {
            System.out.println("element : " + s);
        }
    }
}

/**
 * http://tyrion.iteye.com/blog/2203335
 */
