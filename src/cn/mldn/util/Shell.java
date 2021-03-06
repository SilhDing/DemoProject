package cn.mldn.util;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/7/20 10:03
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/7/20 10:03
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Shell {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N) h = 3*h + 1;
        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < N; i++) {
                // insert a[i] among a[i-h], a[i-2*h], ...
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                    exch(a, j, j-h);
            }
            h /= 3;
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i , int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
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
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] a = {3,5,1,5,2,12,9,4,2,1,32};
        sort(a);
        show(a);
    }
}
