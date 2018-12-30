package cn.mldn.util.Graph;

/**
 * @Description: find connected components
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.Graph
 * @Author: Yihang Ding
 * @CreateDate: 2018/12/29 17:05
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/12/29 17:05
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class CC {

    private boolean[] marked;
    private int[] id;
    private int count;

    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        count = 0;
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s);
                count ++;
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w: G.adj(v)) {
            if (!marked[v]) dfs(G, w);
        }
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int w) {
        return id[w];
    }

    public int count() {
        return count;
    }
}
