package algorithm;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort2 {
    public static int Partition(int a[], int p, int r) {
        int x = a[r - 1];
        int i = p - 1;
        int temp;
        for (int j = p; j <= r - 1; j++) {
            if (a[j - 1] <= x) {
// swap(a[j-1],a[i-1]);
                i++;
                temp = a[j - 1];
                a[j - 1] = a[i - 1];
                a[i - 1] = temp;

            }
        }
//swap(a[r-1,a[i+1-1]);
        temp = a[r - 1];
        a[r - 1] = a[i + 1 - 1];
        a[i + 1 - 1] = temp;

        return i + 1;

    }

    public static void QuickSort(int a[], int p, int r) {
        if (p < r) {
            int q = Partition(a, p, r);
            QuickSort(a, p, q - 1);
            QuickSort(a, q + 1, r);
        }
    }

    public static void main(String[] stra) {

        int a[] = {23, 53, 77, 36, 84, 76, 93, 13, 45};
        QuickSort(a, 1, a.length);

        for (int i = 1; i <= a.length; i++)
            System.out.print(a[i - 1] + " ");

    }
}
