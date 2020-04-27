package cn.mldn.util.String;

import cn.mldn.util.Insertion;

import java.util.Arrays;

/**
 * @Description: MSD string sort algorithm
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.String
 * @Author: Yihang Ding
 * @CreateDate: 2019/1/10 15:34
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2019/1/10 15:34
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class MSD {

    private static int R = 256;  // radix
    private static final int M = 0;  // cutoff for small subarrays
    private static String[] aux; // auxiliary array for distribution

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N-1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        // Cut-off if the number of string is too small
        if (lo + M >= hi) {
            Insertion.sort(a, lo, hi, d);
            return ;
        }

        int[] count = new int[R+2];
        // Compute frequency counts.
        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i], d) + 2] ++;
        }

        // Transfrom counts to indices.
        for (int i = 0; i < R +1; i++) {
            count[i+1] += count[i];
        }

        // Distribute.
        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }

        // Copy back.
        for (int i = lo; i <= hi; i++) {
            // Consdier why i - lo here: because we only sort hi- lo + 1 strings, starting with index 0 in aux
            a[i] = aux[i - lo];
        }

        // Recursive call: process to the next d for each split
        for (int r = 0; r < R; r++) {
            sort(a, lo + count[r], lo + count[r+1] - 1, d + 1);
        }

    }

    private static int charAt(String s, int index) {
        if (index < s.length())
            return s.charAt(index);
        else
            return -1;
    }

    public static void main(String[] args) {
        String[] a = {"she", "sells", "seashells", "by", "the", "sea",
                        "shore", "the", "shells", "she", "sells", "are", "surely", "seashells"};
        sort(a);
        System.out.println(Arrays.toString(a));
        String[] b = {"aaaaaa","aaaaaa","aaaaaa","aaaaaa","aaaaaa","aaaaaa","aaaaaa","aaaaaa"};
        sort(b);
    }
}
