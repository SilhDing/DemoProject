package cn.mldn.util;

/**
 * @Description: check if teh graph is bipartite
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/8/11 10:23
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/8/11 10:23
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class TwoColor {

    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) dfs(G, s);
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w: G.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(G, w);
            }
            else if(color[w] == color[v]) isTwoColorable = false;
        }
    }

    public boolean isBipartite() {
        return isTwoColorable;
    }

}
