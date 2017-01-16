package base;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 17-1-12
 * Time: 下午7:55
 * To change this template use File | Settings | File Templates.
 */
public class HashCode {
    public static void main(String[] args) {
        String a = "123";
        String b = "123";
        String c = new String("123");
        String d = new String("123");

        System.out.println(a == b);
        System.out.println(a == c);

        Me me1 = new Me("gao", 1);
        Me me2 = new Me("gao", 2);

        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(c.hashCode());
        System.out.println(d.hashCode());

        System.out.println(me1.hashCode());
        System.out.println(me2.hashCode());

//        如果你的两个HashMap的key和value全部相同的话, 那么, 它们的hashcode就是相同的
//        尽量 不要用map的hashcode值 作为key
        Map<String,String> map1 = new HashMap<String,String>();
        map1.put("key1","value1");
        Map<String,String> map2 = new HashMap<String,String>();
        map2.put("key2","value2");
        System.out.println(map1.hashCode());
        System.out.println(map2.hashCode());

    }

}
