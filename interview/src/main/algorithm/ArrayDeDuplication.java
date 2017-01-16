package algorithm;

import java.util.*;

/**
 * 数组去重
 */
public class ArrayDeDuplication {
    public static void main(String[] args) {
        // 方法一
        // 先遍历原数组，然后遍历结束集，通过每个数组的元素和结果集中的元素进行比对，若相同则break。若不相同，则存入结果集。
        // 两层循环进行遍历得出最终结果。
        String[] array = {"a","b","c","c","d","e","e","e","a"};
        List<String> result = new ArrayList<String>();
        boolean flag;
        for(int i=0;i<array.length;i++){
            flag = false;
            for(int j=0;j<result.size();j++){
                if(array[i].equals(result.get(j))){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                result.add(array[i]);
            }
        }
        String[] arrayResult = (String[]) result.toArray(new String[result.size()]);
        System.out.println(Arrays.toString(arrayResult));


        // 方法二 通过使用indexOf方法进行判断结果集中是否存在了数组元素。
        //数组去重方法二
        String[] array2 = {"a","b","c","c","d","e","e","e","a"};
        List<String> list = new ArrayList<String>();
        list.add(array2[0]);
        for(int i=1;i<array2.length;i++){
            if(list.toString().indexOf(array2[i]) == -1){
                list.add(array2[i]);
            }
        }
        String[] arrayResult2 = (String[]) list.toArray(new String[list.size()]);
        System.out.println(Arrays.toString(arrayResult2));

        // 方法三 嵌套循环，进行比较获取满足条件结果集。
        String[] array3 = {"a","b","c","c","d","e","e","e","a"};
        List<String> list3 = new ArrayList<String>();
        for(int i=0;i<array3.length;i++){
            for(int j=i+1;j<array3.length;j++){
                if(array3[i] == array3[j]){
                    j = ++i;
                }
            }
            list3.add(array3[i]);
        }
        String[] arrayResult3 = (String[]) list3.toArray(new String[list3.size()]);
        System.out.println(Arrays.toString(arrayResult3));

        // 方法四 先使用java提供的数组排序方法进行排序，然后进行一层for循环，进行相邻数据的比较即可获得最终结果集。
        String[] array4 = {"a","b","c","c","d","e","e","e","a"};
        Arrays.sort(array4);
        List<String> list4 = new ArrayList<String>();
        list4.add(array4[0]);
        for(int i=1;i<array4.length;i++){
            if(!array4[i].equals(list4.get(list4.size()-1))){
                list4.add(array4[i]);
            }
        }
        String[] arrayResult4 = (String[]) list4.toArray(new String[list4.size()]);
        System.out.println(Arrays.toString(arrayResult4));

        // 方法五 加入set方法进行添加，虽然是无序排列，但是也更方便的解决了去重的问题。
        String[] array5 = {"a","b","c","c","d","e","e","e","a"};
        Set<String> set = new HashSet<String>();
        for(int i=0;i<array5.length;i++){
            set.add(array5[i]);
        }
        String[] arrayResult5 = (String[]) set.toArray(new String[set.size()]);
        System.out.println(Arrays.toString(arrayResult5));
    }
}
