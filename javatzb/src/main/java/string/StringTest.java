package string;

/**
 * hashcode 是为了算法快速定位数据而存在的，
 * equals 是为了对比真实值而存在的
 *
 * 假如你希望对比两个对象是否是同一个对象，则完全可以直接用“==”来对比，而不
 需要用hashCode()，因为直接对比地址值说明两个对象是否为同一个对象才是最靠谱的
 *
 *
 *
 */
public class StringTest {
    private void test1() {
        String a = "a" + "b" + 1;
        String b = "ab1";
        System.out.println(a == b);
    }

    private static String getA() {return"a";}
    public void test2() {
        String a = "a";
        final String c = "a";
        String b = a + "b";
        String d = c + "b";
        String e = getA() + "b";
        String compare = "ab";
        System.out.println(b == compare);
        System.out.println(d == compare);
        System.out.println(e == compare);
    }

    public static void test3() {
        String a = "a";
        String b = a + "b";
        String c = "ab";
        String d = new String(b);
        System.out.println(b == c);
        System.out.println(c == d);
        System.out.println(c == d.intern());
        System.out.println(b.intern() == d.intern());
    }

    public static void main(String[] args) {
        StringTest stringTest = new StringTest();
        stringTest.test1();
        stringTest.test2();
        stringTest.test3();
    }
}
