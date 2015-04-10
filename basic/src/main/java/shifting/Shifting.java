/*
 * Copyright (c) 2015, www.jd.com. All rights reserved.
 *
 * 警告：本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或散布本程序的部分或全部、以及其他
 * 任何侵害著作权人权益的行为，将承受严厉的民事和刑事处罚，对已知的违反者将给予法律范围内的全面制裁。
 */


package shifting;

/**
 * @author superGenius
 * java移位操作
 *基础知识：java中类型的二进制表示
 *
 *一.int的是32位。long的是64位。
 *如int i = 1;
 * i的二进制原码表示为：
 * 00000000000000000000000000000001
 *long l = 1;
 *l的二进制原码表示为：   *0000000000000000000000000000000000000000000000000000000000000001
 *
 *二.原码——符号位为0表示正数，为1表示负数；
其余各位等同于真值的绝对值。
如：0000000000000010B=2，1000000000000010B=-2
反码——符号位的用法及正数的表示与“原码”一样；
负数的表示是在“原码”表示的基础上通过将符号位以外
的各位取反来获得的。
如：0000000000000010B=2，1111111111111101B=-2
补码——符号位的用法及正数的表示与“原码”一样；
负数的表示是在“反码”的基础上通过加1来获得的。
如：00000010B=2，11111110B=-2

三。常用的操作
& 与。    全1为1， 有0为0。　　任何数与0与都等于0。　　
| 或。      有1为1， 全0为0。　　任何数与0或都等于原值。
~ 非。     逐位取反
^ 异或。  相同为0，相异为1。      任何数与0异或都等于原值。

 */
public class Shifting {
    public static void main(String[] args)
    {

        System.out.println(1<<1);//1左移一位从0001->00010，相当于乘以2  结果2
        System.out.println(4>>2);//相当于除以4  结果1
        System.out.println(1&2);//1->0001 2->0010 逐位比较  返回0000 结果0
        System.out.println(1^1);//相同的返回0
        System.out.println(1|2);//1->0001 2->0010 逐位比较  返回0011结果3

        // int的是32位
        System.out.println("int的是32位" + Integer.toBinaryString(Integer.MAX_VALUE));

        // long的是64位
        System.out.println("long的是64位" + Long.toBinaryString(Long.MAX_VALUE));

        for (int i = 0;i < 8 ;i++) {
            int x = 1 << i;
            System.out.println(Integer.toBinaryString(x) + " ");
        }

        for (int i = 0;i < 31 ;i++) {
            System.out.print( Integer.toBinaryString(0x40000000 >> i) + " ");
        }

    }
} 
