package cn.mldn.util.String;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.String
 * @Author: Yihang Ding
 * @CreateDate: 4/25/20 4:01 PM
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 4/25/20 4:01 PM
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class BoyerMoore {
    private int[] right;
    private String pat;

    public BoyerMoore(String pat) {
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        right = new int[R];
        for (int c = 0 ; c < R; c++) {
            right[c] = -1;
        }
        for (int j = 0; j < M; j++) {
            right[pat.charAt(j)] = j;
        }
    }

    public int search(String txt) {
        int N = txt.length();
        int M = pat.length();
        int skip;
        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;  // the increment for i
            for (int j = M - 1; j >= 0; j --) {
                // compare pat.charAt(i) and txt.charAt(i+j_
                if (pat.charAt(j) != txt.charAt(i+j)) {
                    skip = j - right[txt.charAt(i+j)];
                    if (skip < 1) skip = 1; // if value from `right` is -1, then skip = j + 1, which is the second case
                    break;
                }
            }
            if (skip == 0) return i;
        }
        return N;
    }
}
