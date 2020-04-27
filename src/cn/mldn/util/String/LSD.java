package cn.mldn.util.String;

import java.util.Arrays;

/**
 * @Description: LSD string sort algorithm
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.String
 * @Author: Yihang Ding
 * @CreateDate: 2019/1/10 14:33
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2019/1/10 14:33
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class LSD {
    public static void sort(String[] a, int W) {
        // W is the length of each string in a
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];

        for (int d = W - 1; d >= 0; d--) {
            // Sort by key-indexed counting on dth char.

            int[] count = new int[R+1];
            // Computer frequency counts.
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d) + 1] ++;
            }

            // Transform counts to indices.
            for (int r = 0; r < R; r++) {
                count[r+1] += count[r];
            }

            // Distribute.
            for (int i = 0; i < N; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }

            // Copy back.
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }
        }
    }

    public static void main(String[] args) {
        String[] a  = {"43FRFN", "423FFE", "D439JN", "10NE2E",
                        "DDERFR", "340FEW", "104FDS", "R4053N"};
        LSD.sort(a, 6);
        System.out.println(Arrays.toString(a));
    }
}
