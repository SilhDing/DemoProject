package cn.mldn.util.String;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.String
 * @Author: Yihang Ding
 * @CreateDate: 4/23/20 10:01 PM
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 4/23/20 10:01 PM
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class KMP {

    private String pat;
    private int[][] dfa;

    public KMP(String pat) {
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1;
        for (int j = 1, X = 0; j < M; j++) {
            for (int c = 0; c < R; c ++) {
                dfa[c][j] = dfa[c][X];
            }
            dfa[pat.charAt(j)][j] = j + 1;

            // Update X
            X = dfa[pat.charAt(j)][X];
        }
    }

    public int search(String txt) {
        int i, j;
        int N = txt.length(), M = this.pat.length();
        for(i = 0, j = 0; i < N && j < M; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == M) return i - M;
        else return N;
    }

}
