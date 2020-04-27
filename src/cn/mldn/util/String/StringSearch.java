package cn.mldn.util.String;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.String
 * @Author: Yihang Ding
 * @CreateDate: 4/20/20 11:09 PM
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 4/20/20 11:09 PM
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class StringSearch {
    // This is the brute-force substring search

    public static int search(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        for (int i = 0; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j ++) {
                if (txt.charAt(i + j) != pat.charAt(j))
                    break;
            }
            if (j == M) return i;
        }
        return N;
    }

    public static int search2(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        int i, j;
        for (i = 0, j = 0; i < N && j < M; i ++) {
            if (txt.charAt(i) == pat.charAt(j)) {
                j++;
            } else {
                i -= j;
                j = 0;
            }
        }

        if (j == M) return i - M;
        else return N;

    }

    public static void main(String[] args) {
        System.out.println(search("ABRA", "ABACADABRAC"));
        System.out.println(search("ABRA", "ABACADABRBC"));
        System.out.println(search2("ABRA", "ABACADABRAC"));
        System.out.println(search2("ABRA", "ABACADABRBC"));
    }
}
