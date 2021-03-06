package cn.mldn.util.String;

import java.util.Arrays;

/**
 * @Description: Three-way string quicksort
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.String
 * @Author: Yihang Ding
 * @CreateDate: 2019/1/11 15:09
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2019/1/11 15:09
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Quick3string {

    public static void sort(String[] a) {
        sort(a, 0, a.length - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (lo >= hi) return;

        int lt = lo, gt = hi;
        int i = lo + 1;
        int v = charAt(a[lo], d);

        while (i <= gt) {
            int t = charAt(a[i], d);

            if (t < v) exch(a, lt++, i++);
            else if (t > v) exch(a, i, gt--);
            else i ++;
        }

        sort(a, lo, lt-1, d);
        if (v >= 0) sort(a, lt, gt, d + 1);
        sort(a, gt + 1, hi, d);
    }

    private static int charAt(String s, int index) {
        if (index < s.length())
            return s.charAt(index);
        else
            return -1;
    }

    private static void exch(String[] a, int i, int j) {
        String tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        String[] a = {"she", "sells", "seashells", "by", "the", "sea",
                "shore", "the", "shells", "she", "sells", "are", "surely", "seashells"};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
