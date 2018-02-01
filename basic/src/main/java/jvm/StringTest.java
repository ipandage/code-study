package jvm;

/**
 * @author gxg
 * @date 2018/1/23 下午3:48
 */
public class StringTest {
    public static void main(String[] args) {
        String a = "java"; // 存储在常量池中
        String c = new String("java"); // 堆上申请内存

        System.out.println(a == c);

//        String a = "hello ";
//        String b = "world";
//        String c = a + b;  字符串变量的连接动作在编译阶段会被转化成StringBuilder的append操作
//                            c 指向java堆新建的String对象
//        String d = "hello world";

//        c == d 成立吗？

    }
}