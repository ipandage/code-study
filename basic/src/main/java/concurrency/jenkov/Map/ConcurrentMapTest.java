package concurrency.jenkov.Map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMapTest {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentMap concurrentMap = new ConcurrentHashMap();

        concurrentMap.put("key", "value");

        Object value = concurrentMap.get("key");

        System.out.println(value);
    }
}
