package algorithm;

import java.util.BitSet;

/**
 * 位图
 */
public class BitMap {
    byte[] tem;

    public BitMap(int length) {
        this.tem = new byte[length];
    }

    public void add(int num) {
        if (num < tem.length) {
            if (tem[num] != 1) {
                tem[num] = 1;
            }
        }
    }

    public boolean contain(int num) {
        if (num < tem.length) {
            if (tem[num] == 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
  /*运行前内存*/
        long beforeMemory = Runtime.getRuntime().totalMemory();
        long start1=System.currentTimeMillis();
        BitSet set = new BitSet(2000000000);
        for (int i = 0; i < 2000000000; i++) {
   /*假设898989这个数不在20亿个数里面*/
            if (i != 898989) {
                set.set(i, true);
            }
        }
  /*创建20亿个数后所占内存*/
        long afterMemory = Runtime.getRuntime().totalMemory();
        long end1=System.currentTimeMillis();
        System.out.println("总共内存使用:" + (afterMemory - beforeMemory) / 1024 / 1024 + "MB");
        System.out.println("存入内存耗时:"+(end1-start1)+"毫秒");
        long start2 = System.currentTimeMillis();
        boolean isExit1=set.get(898989);
        boolean isExit2=set.get(900000);

        long end2 = System.currentTimeMillis();
  /*输出在20亿个数中判断898989是否包含在里面*/
        System.out.println(isExit1);
        System.out.println("20个亿中"+(isExit1?"包含":"不包含")+898989);
        System.out.println("20个亿中"+(isExit2?"包含":"不包含")+900000);
        System.out.println("查询用时:"+(end2 - start2)+"毫秒");
    }
}
