package cn.mldn.util.String;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.String
 * @Author: Yihang Ding
 * @CreateDate: 4/25/20 8:07 PM
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 4/25/20 8:07 PM
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class RabinKarp {
    private String pat;
    private int M;
    private long patHash;
    private long Q;
    private int R = 256;
    private long RM;  // R^(M - 1) % Q

    public RabinKarp(String pat) {
        this.pat = pat;
        this.M = pat.length();
        Q = 997; // A prime number
        RM = 1;
        for (int i = 1; i <= M - 1; i++) {
            RM = (R * RM ) % Q;
        }
        patHash = hash(pat, M);
    }

    public long hash(String key, int M) {
        long h = 0;
        for (int j = 0; j < M ; j++) {
            h = (R * h + key.charAt(j)) % Q;
        }
        return h;
    }

    private int search(String txt) {
        int N = txt.length();
        long txtHash = hash(txt, M); // only get the hash value for the first M chars
        if (patHash == txtHash) return 0;
        for (int i = M ; i < N; N++) {
            txtHash = (txtHash + Q - RM * txt.charAt(i-M) % Q) % Q;
            // Adding Q to make sure we can always get the positive value
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            if (patHash == txtHash) {
                return i - M + 1;
            }
        }
        return N;
    }
}
