package cn.mldn.util;

/**
 * @Description: Dpth-first search to find connected components in a graph
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/8/10 22:02
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/8/10 22:02
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class CC {
    private boolean[] marked;
    private int[] id;
    private int count;  // the number of connected components

    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int s = 0; s < G.V(); s ++) {
            if (!marked[s]) {
                dfs(G, s);
                count ++;
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count; // mark the component identifier
        for (int w: G.adj(v)) {
            if (!marked[w])
                dfs(G, w);
        }
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
        // connected if they are in the same component
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {

    }
}
