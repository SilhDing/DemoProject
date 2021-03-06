package cn.mldn.util;

/**
 * @Description: heapsort
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/7/20 12:01
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/7/20 12:01
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Heap {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int k = N/2; k >= 1; k --)
            sink(a, k, N);
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }

    }

    private static void sink(Comparable[] a, int k, int N) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(a, j, j+1)) j++;
            if (!less(a, k, j)) break;
            exch(a, k, j);
            k = j;
        }
    }

    private static boolean less(Comparable[] a, int i , int j) {
        // 要做一个偏移
        return a[i-1].compareTo(a[j-1]) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        // 注意这里要特殊处理一下，因为heap中的序号和array index还是不一样的
        Comparable t = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = t;
    }

    private static void show(Comparable[] a) {
        // print the array, on a single line
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        // test whether the array entries are in order
        for (int i = 1; i < a.length; i++) {
            if (less(a, i, i-1)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] a = {3,5,1,5,2,12,9,4,2,1,32};
        sort(a);
        show(a);
    }

}
