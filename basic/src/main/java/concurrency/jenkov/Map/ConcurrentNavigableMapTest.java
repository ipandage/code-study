package concurrency.jenkov.Map;


import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentNavigableMapTest {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentNavigableMap map = new ConcurrentSkipListMap();
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");
        ConcurrentNavigableMap headMap = map.headMap("2");
        System.out.println(headMap);

        ConcurrentNavigableMap tailMap = map.tailMap("2");
        System.out.println(tailMap);

        ConcurrentNavigableMap subMap = map.subMap("2", "3");
        System.out.println(subMap);

    }
}
